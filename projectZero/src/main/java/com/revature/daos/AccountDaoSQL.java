package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Account;
import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class AccountDaoSQL implements AccountDao {

	private Logger log = Logger.getRootLogger();

	Account extractAccount(ResultSet rs) throws SQLException {
		int rsId = rs.getInt("account_id");
		int rsBalance = rs.getInt("balance");
		int rsUser = rs.getInt("user_ID");
		String rsAccountType = rs.getString("account_type");
		boolean isOpen = rs.getString("is_open").equals("Yes");
		return new Account(rsId, rsBalance, rsAccountType, new User(rsUser, null, null, false), isOpen);
	}

	public int open(Account a) {
		log.debug("attempting to find accounts from DB");
		try (Connection c = ConnectionUtil.getConnection()) {
			System.out.println(a.getOwner().getId());
			String sql = "INSERT INTO project_accounts (account_id, user_id, account_type) VALUES (accounts_id_seq.nextval, ?, ?)";

			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, a.getOwner().getId());
			ps.setString(2, a.getAccountType());
			System.out.println(ps.toString());
			return ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	public List<Account> findAll() {
		log.debug("attempting to find accounts from DB");
		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM account";

			PreparedStatement ps = c.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			List<Account> accounts = new ArrayList<>();
			while (rs.next()) {
				accounts.add(extractAccount(rs));
			}

			return accounts;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public Account findByID(int i) {
		
		try (Connection c = ConnectionUtil.getConnection()) {

		String sql = "SELECT * from project_accounts WHERE account_id = ?";

		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, i);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return extractAccount(rs);
		
	}

	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
}

	public void close(int i) {
		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "Update project_accounts SET is_Open = 'No' WHERE account_id = ?";

			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, i);
			ps.executeUpdate();
		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Account> findbyOwner(User u) {

		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM project_accounts WHERE user_id = ?";

			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, u.getId());

			ResultSet rs = ps.executeQuery();
			List<Account> accounts = new ArrayList<>();
			while (rs.next()) {
				accounts.add(extractAccount(rs));
			}

			return accounts;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int updateBalance(int i, int j) {
		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "Select balance from project_accounts WHERE account_id = ?";

			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, i);
			ResultSet rs = ps.executeQuery();
			rs.next();
			int bal = rs.getInt("balance");
			bal = bal + j;
			String sql2 = "update project_accounts set balance = ? WHERE account_id = ?";
			PreparedStatement ps2 = c.prepareStatement(sql2);
			ps2.setInt(1,bal);
			ps2.setInt(2, i);

			return ps2.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}

	}
}