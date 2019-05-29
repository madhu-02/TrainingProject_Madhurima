package com.tadigital.ecommerce.customer.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.tadigital.ecommerce.customer.entity.Customer;

public class CustomerDao extends Dao {
	public boolean selectCustomerByEmailAndPassword(Customer customer) {
		boolean status = false;

		Connection con = openConnection();
		Statement stmt = openStatement(con);
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM customer_information WHERE cust_email = '" + customer.getEmail() + "' AND cust_pwd = '"
					+ customer.getPassword() + "'";

			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				status = true;

				customer.setFirstName(rs.getString(1));
				customer.setLastName(rs.getString(2));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			closeResultSet(rs);
			closeStatement(stmt);
			closeConnection(con);
		}

		return status;
	}

	public boolean selectCustomerByEmail(Customer customer) {
		boolean status = false;

		Connection con = openConnection();
		Statement stmt = openStatement(con);
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM customer_information WHERE cust_email = '" + customer.getEmail() + "'";

			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				status = true;
			}
		} catch (SQLException sqle) {

			sqle.printStackTrace();
		} finally {
			closeResultSet(rs);
			closeStatement(stmt);
			closeConnection(con);
		}

		return status;
	}

	public boolean updatePassword(Customer customer) {
		boolean status = false;

		Connection con = openConnection();
		Statement stmt = openStatement(con);

		try {
			String sql = "UPDATE customer_information SET cust_pwd ='" + customer.getPassword() + "' WHERE cust_email = '"
					+ customer.getEmail() + "'";

			int row = stmt.executeUpdate(sql);
			if (row != 0) {
				status = true;
			}
		} catch (SQLException sqle) {

			sqle.printStackTrace();
		} finally {
			closeStatement(stmt);
			closeConnection(con);
		}

		return status;
	}

	public boolean updateCustomerDet(Customer customer, String x) {
		boolean status = false;

		Connection con = openConnection();
		Statement stmt = openStatement(con);

		try {

			StringBuffer sql = new StringBuffer("UPDATE customer_information SET ");
			boolean flag = false;

			if (customer.getFirstName() != "") {
				sql.append("cust_fname='" + customer.getFirstName() + "'");
				flag = true;
			}

			if (customer.getLastName() != "") {
				if (flag)
					sql.append(",");
				sql.append("cust_lname='" + customer.getLastName() + "'");
				flag = true;
			}


			if (customer.getEmail() != "") {
				if (flag)
					sql.append(",");
				sql.append("cust_email='" + customer.getEmail() + "'");
				flag = true;
			}

			sql.append(" WHERE cust_email='" + x + "'");

			int row = stmt.executeUpdate(sql.toString());
			if (row != 0) {
				status = true;
			}
		} catch (SQLException sqle) {

			sqle.printStackTrace();
		} finally {
			closeStatement(stmt);
			closeConnection(con);
		}

		return status;
	}

	public boolean insertCustomer(Customer customer) {
		boolean status = false;

		Connection con = openConnection();
		Statement stmt = openStatement(con);

		try {
			String sql = "INSERT INTO customer_information(cust_fname, cust_lname, cust_email, cust_pwd) " + "VALUES('"
					+ customer.getFirstName() + "', '" + customer.getLastName() + "', '" + customer.getEmail() + "', '"
					+ customer.getPassword() + "')";

			int rows = stmt.executeUpdate(sql);
			if (rows != 0) {
				status = true;
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			closeStatement(stmt);
			closeConnection(con);
		}

		return status;
	}
}