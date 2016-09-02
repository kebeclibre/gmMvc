package lcs.prs.goingmobile.helperclasses;

import lcs.prs.goingmobile.entities.Transaction;

public class TransactionWrapper {

		private Transaction transaction;
		private String username;
		private String password;
		
		public Transaction getTransaction() {
			return transaction;
		}
		public void setTransaction(Transaction transaction) {
			this.transaction = transaction;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
}
