package lcs.prs.goingmobile.helperclasses;

import lcs.prs.goingmobile.entities.Transaction;

public class TransactionWrapper {

		private Transaction transaction;
		private String clientUsername;
		private String clientPass;
		public Transaction getTransaction() {
			return transaction;
		}
		public void setTransaction(Transaction transaction) {
			this.transaction = transaction;
		}
		public String getClientUsername() {
			return clientUsername;
		}
		public void setClientUsername(String clientUsername) {
			this.clientUsername = clientUsername;
		}
		public String getClientPass() {
			return clientPass;
		}
		public void setClientPass(String clientPass) {
			this.clientPass = clientPass;
		}
		
	
}
