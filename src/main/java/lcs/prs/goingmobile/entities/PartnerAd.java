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
 * Partnerads generated by hbm2java
 */
@Entity
@Table(name = "partnerads", catalog = "goingmobile")
public class PartnerAd implements java.io.Serializable {

	private Integer id;
	private Partner partners;
	private Date addedDate;
	private String adName;
	private String titleAd;
	private String descriptionAd;
	private String remark;
	private boolean isActive;
	private boolean isApproved;

	public PartnerAd() {
	}

	public PartnerAd(Partner partners, Date addedDate, String adName, String titleAd, String descriptionAd,
			boolean isActive, boolean isApproved) {
		this.partners = partners;
		this.addedDate = addedDate;
		this.adName = adName;
		this.titleAd = titleAd;
		this.descriptionAd = descriptionAd;
		this.isActive = isActive;
		this.isApproved = isApproved;
	}

	public PartnerAd(Partner partners, Date addedDate, String adName, String titleAd, String descriptionAd,
			String remark, boolean isActive, boolean isApproved) {
		this.partners = partners;
		this.addedDate = addedDate;
		this.adName = adName;
		this.titleAd = titleAd;
		this.descriptionAd = descriptionAd;
		this.remark = remark;
		this.isActive = isActive;
		this.isApproved = isApproved;
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
	@JoinColumn(name = "partner_Id", nullable = false)
	public Partner getPartners() {
		return this.partners;
	}

	public void setPartners(Partner partners) {
		this.partners = partners;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "addedDate", nullable = false, length = 10)
	public Date getAddedDate() {
		return this.addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	@Column(name = "adName", nullable = false, length = 45)
	public String getAdName() {
		return this.adName;
	}

	public void setAdName(String adName) {
		this.adName = adName;
	}

	@Column(name = "titleAd", nullable = false, length = 45)
	public String getTitleAd() {
		return this.titleAd;
	}

	public void setTitleAd(String titleAd) {
		this.titleAd = titleAd;
	}

	@Column(name = "descriptionAd", nullable = false, length = 45)
	public String getDescriptionAd() {
		return this.descriptionAd;
	}

	public void setDescriptionAd(String descriptionAd) {
		this.descriptionAd = descriptionAd;
	}

	@Column(name = "remark", length = 45)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "isActive", nullable = false)
	public boolean isIsActive() {
		return this.isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Column(name = "isApproved", nullable = false)
	public boolean isIsApproved() {
		return this.isApproved;
	}

	public void setIsApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

}