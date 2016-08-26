package lcs.prs.goingmobile.entities;
// Generated Aug 19, 2016 11:55:51 AM by Hibernate Tools 5.1.0.Beta1

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import lcs.prs.goingmobile.interfaces.UserInterface;

/**
 * Clients generated by hbm2java
 */
@Entity
@Table(name = "clients", catalog = "goingmobile")
public class Client implements java.io.Serializable, UserInterface {

	private Integer id;
	@NotEmpty(message="Ce champ est obligatoire")
	private String username;
	@NotEmpty(message="Ce champ est obligatoire") @Email(message="email invalide")
	private String email;
	@NotEmpty(message="Ce champ est obligatoire")
	private String firstName;
	@NotEmpty(message="Ce champ est obligatoire")
	private String lastName;
	@NotEmpty(message="Ce champ est obligatoire")
	private String password;
	private boolean isActive;
	private Date registrationDate;
	private String profilePicture;
	private float kmsTotal;
	private double gmPointsTotal;
	private double gmPointsHistoryCumul;
	private float kmsHistoryCumul;
	private String bonus;
	private Boolean isPublic;
	private Set<Addresse> addresseses = new HashSet<Addresse>(0);
	private Set<Journey> journeyses = new HashSet<Journey>(0);
	private Set<Transaction> transactionses = new HashSet<Transaction>(0);

	public Client() {
	}

	public Client(String username, String email, String firstName, String lastName, String password, boolean isActive,
			Date registrationDate, float kmsTotal, double gmPointsTotal, double gmPointsHistoryCumul,
			float kmsHistoryCumul) {
		this.username = username;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.isActive = isActive;
		this.registrationDate = registrationDate;
		this.kmsTotal = kmsTotal;
		this.gmPointsTotal = gmPointsTotal;
		this.gmPointsHistoryCumul = gmPointsHistoryCumul;
		this.kmsHistoryCumul = kmsHistoryCumul;
	}

	public Client(String username, String email, String firstName, String lastName, String password, boolean isActive,
			Date registrationDate, String profilePicture, float kmsTotal, double gmPointsTotal,
			double gmPointsHistoryCumul, float kmsHistoryCumul, String bonus, Boolean isPublic,
			Set<Addresse> addresseses, Set<Journey> journeyses, Set<Transaction> transactionses) {
		this.username = username;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.isActive = isActive;
		this.registrationDate = registrationDate;
		this.profilePicture = profilePicture;
		this.kmsTotal = kmsTotal;
		this.gmPointsTotal = gmPointsTotal;
		this.gmPointsHistoryCumul = gmPointsHistoryCumul;
		this.kmsHistoryCumul = kmsHistoryCumul;
		this.bonus = bonus;
		this.isPublic = isPublic;
		this.addresseses = addresseses;
		this.journeyses = journeyses;
		this.transactionses = transactionses;
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

	@Column(name = "username", nullable = false, length = 20)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "email", nullable = false, length = 40)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "firstName", nullable = false, length = 20)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "lastName", nullable = false, length = 20)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "password", nullable = false, length = 200)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "isActive", nullable = false)
	public boolean isIsActive() {
		return this.isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "registrationDate", nullable = false, length = 10)
	public Date getRegistrationDate() {
		return this.registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	@Column(name = "profilePicture")
	public String getProfilePicture() {
		return this.profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	@Column(name = "kmsTotal", nullable = false, precision = 12, scale = 0)
	public float getKmsTotal() {
		return this.kmsTotal;
	}

	public void setKmsTotal(float kmsTotal) {
		this.kmsTotal = kmsTotal;
	}

	@Column(name = "gmPointsTotal", nullable = false, precision = 22, scale = 0)
	public double getGmPointsTotal() {
		return this.gmPointsTotal;
	}

	public void setGmPointsTotal(double gmPointsTotal) {
		this.gmPointsTotal = gmPointsTotal;
	}

	@Column(name = "gmPointsHistoryCumul", nullable = false, precision = 22, scale = 0)
	public double getGmPointsHistoryCumul() {
		return this.gmPointsHistoryCumul;
	}

	public void setGmPointsHistoryCumul(double gmPointsHistoryCumul) {
		this.gmPointsHistoryCumul = gmPointsHistoryCumul;
	}

	@Column(name = "kmsHistoryCumul", nullable = false, precision = 12, scale = 0)
	public float getKmsHistoryCumul() {
		return this.kmsHistoryCumul;
	}

	public void setKmsHistoryCumul(float kmsHistoryCumul) {
		this.kmsHistoryCumul = kmsHistoryCumul;
	}

	@Column(name = "bonus", length = 20)
	public String getBonus() {
		return this.bonus;
	}

	public void setBonus(String bonus) {
		this.bonus = bonus;
	}

	@Column(name = "isPublic")
	public Boolean getIsPublic() {
		return this.isPublic;
	}

	public void setIsPublic(Boolean isPublic) {
		this.isPublic = isPublic;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "clients")
	public Set<Addresse> getAddresseses() {
		return this.addresseses;
	}

	public void setAddresseses(Set<Addresse> addresseses) {
		this.addresseses = addresseses;
	}



	@Override
	public String toString() {
		return "Client [id=" + id + ", username=" + username + ", email=" + email + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", password=" + password + ", isActive=" + isActive + ", registrationDate="
				+ registrationDate + ", profilePicture=" + profilePicture + ", kmsTotal=" + kmsTotal
				+ ", gmPointsTotal=" + gmPointsTotal + ", gmPointsHistoryCumul=" + gmPointsHistoryCumul
				+ ", kmsHistoryCumul=" + kmsHistoryCumul + ", bonus=" + bonus + ", isPublic=" + isPublic
				+ ", addresseses=" + addresseses + "]";
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "clients")
	public Set<Journey> getJourneyses() {
		return this.journeyses;
	}

	public void setJourneyses(Set<Journey> journeyses) {
		this.journeyses = journeyses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "clients")
	public Set<Transaction> getTransactionses() {
		return this.transactionses;
	}

	public void setTransactionses(Set<Transaction> transactionses) {
		this.transactionses = transactionses;
	}

	@Override
	public void insertUsername(String username) {
		setUsername(username);
		
	}

	@Override
	public void insertPassword(String pass) {
		setPassword(pass);
		
	}

	@Override
	public void insertEmail(String email) {
		setEmail(email);
		
	}

	@Override
	public void insertMainMetaData(String meta1) {
		setLastName(meta1);
		
	}

	@Override
	public void insertSecondMetaData(String meta2) {
		setFirstName(meta2);
		
	}

	@Override
	public String collectMainMetaData() {
		return getLastName();
	}

	@Override
	public String collectSecondMetaData() {
		return getFirstName();
	}

	@Override
	public String collectEmail() {
		return getEmail();
	}

}
