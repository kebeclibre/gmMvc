package lcs.prs.goingmobile.entities;
// Generated Aug 19, 2016 11:55:51 AM by Hibernate Tools 5.1.0.Beta1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Transactions generated by hbm2java
 */
@Entity
@Table(name = "transactions", catalog = "goingmobile")
public class Transaction implements java.io.Serializable {

	private Integer id;
	private Client clients;
	private Partner partners;
	private double gmPointsEngaged;
	private String descriptionTransaction;
	private String commentPartner;
	private String commentClient;
	private Date transactionDate;

	public Transaction() {
	}

	public Transaction(Client clients, Partner partners, double gmPointsEngaged, Date transactionDate) {
		this.clients = clients;
		this.partners = partners;
		this.gmPointsEngaged = gmPointsEngaged;
		this.transactionDate = transactionDate;
	}

	public Transaction(Client clients, Partner partners, double gmPointsEngaged, String descriptionTransaction,
			String commentPartner, String commentClient, Date transactionDate) {
		this.clients = clients;
		this.partners = partners;
		this.gmPointsEngaged = gmPointsEngaged;
		this.descriptionTransaction = descriptionTransaction;
		this.commentPartner = commentPartner;
		this.commentClient = commentClient;
		this.transactionDate = transactionDate;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Client_Id", nullable = false)
	public Client getClients() {
		return this.clients;
	}

	public void setClients(Client clients) {
		this.clients = clients;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Partner_Id", nullable = false)
	public Partner getPartners() {
		return this.partners;
	}

	public void setPartners(Partner partners) {
		this.partners = partners;
	}

	@Column(name = "gmPointsEngaged", nullable = false, precision = 22, scale = 0)
	public double getGmPointsEngaged() {
		return this.gmPointsEngaged;
	}

	public void setGmPointsEngaged(double gmPointsEngaged) {
		this.gmPointsEngaged = gmPointsEngaged;
	}

	@Column(name = "descriptionTransaction", length = 65535)
	public String getDescriptionTransaction() {
		return this.descriptionTransaction;
	}

	public void setDescriptionTransaction(String descriptionTransaction) {
		this.descriptionTransaction = descriptionTransaction;
	}

	@Column(name = "commentPartner", length = 65535)
	public String getCommentPartner() {
		return this.commentPartner;
	}

	public void setCommentPartner(String commentPartner) {
		this.commentPartner = commentPartner;
	}

	@Column(name = "commentClient", length = 65535)
	public String getCommentClient() {
		return this.commentClient;
	}

	public void setCommentClient(String commentClient) {
		this.commentClient = commentClient;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "transactionDate", nullable = false, length = 19)
	public Date getTransactionDate() {
		return this.transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

}
