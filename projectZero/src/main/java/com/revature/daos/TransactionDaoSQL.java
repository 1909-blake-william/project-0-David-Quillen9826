package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Account;
import com.revature.models.Transaction;
import com.revature.util.ConnectionUtil;

public class TransactionDaoSQL implements TransactionDao {

	private Logger log = Logger.getRootLogger();

	Transaction extractTransaction(ResultSet rs) throws SQLException {
		int rsId = rs.getInt("transaction_id");
		String rsTransactionType = rs.getString("transaction_type");
		double rsAmount = rs.getInt("amount");
		int rsAccount = rs.getInt("account_id");
		String time = rs.getString("transaction_time");
		return new Transaction(rsId, rsAmount, rsTransactionType, new Account(rsAccount, 0, null, null, false), time);
	}

	@Override
	public int deposit(int d, int i) {
		// write a deposit transaction
		log.debug("attempting to log deposit to DB");
		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "INSERT INTO project_transactions (transaction_id, transaction_type, amount, account_id, transaction_time) "
					+ "VALUES (transaction_id_seq.nextval, 'deposit', ?, ?, ?)";
			PreparedStatement ps = c.prepareStatement(sql);

			ps.setInt(1, d);
			ps.setInt(2, i);
			ps.setString(3, Instant.now().toString());
			return ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int withdraw(int w, int i) {		// write a deposit transaction
		log.debug("attempting to log withdraw to DB");
		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "INSERT INTO project_transactions (transaction_id, transaction_type, amount, account_id, transaction_time) "
					+ "VALUES (transaction_id_seq.nextval, 'withdraw', ?, ?, ?)";
			PreparedStatement ps = c.prepareStatement(sql);

			ps.setInt(1, w);
			ps.setInt(2, i);
			ps.setString(3, Instant.now().toString());
			return ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<Transaction> findAll() {
		log.debug("attempting to find transactions from DB");
		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM project_transactions";

			PreparedStatement ps = c.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			List<Transaction> transactions = new ArrayList<>();
			while (rs.next()) {
				transactions.add(extractTransaction(rs));
			}

			return transactions;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Transaction> findByAccount(Account a) {
		log.debug("attempting to find transactions from DB");
	try (Connection c = ConnectionUtil.getConnection()) {

		String sql = "SELECT * FROM project_transactions WHERE account_id = ?";

		PreparedStatement ps = c.prepareStatement(sql);
		
		ps.setInt(1, a.getId());

		ResultSet rs = ps.executeQuery();
		List<Transaction> transactions = new ArrayList<>();
		while (rs.next()) {
			transactions.add(extractTransaction(rs));
		}

		return transactions;

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
	}

}
