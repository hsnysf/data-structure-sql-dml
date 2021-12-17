package person.dto;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import person.annotation.Column;
import person.annotation.JoinColumn;

public class PersonDTO extends CommonDTO {

	@Column("prsn_id")
	private Integer id;
	@Column("prsn_name")
	private String name;
	@Column("prsn_gender")
	private Character gender;
	@Column("prsn_age")
	private Short age;
	@Column("prsn_cpr")
	private Integer cpr;
	@Column("prsn_account_no")
	private Long accountNo;
	@Column("prsn_gpa")
	private Float gpa;
	@Column("prsn_salary")
	private Double salary;
	@Column("prsn_annual_income")
	private BigDecimal annualIncome;
	@Column("prsn_date_of_birth")
	private Date dateOfBirth;
	@Column("prsn_registration_date_time")
	private Timestamp registrationDateTime;
	@Column("prsn_sleep_time")
	private Time sleepTime;
	@Column("prsn_graduated")
	private Boolean graduated;
	@Column("prsn_certificates")
	private List<String> certificates;
	
	@JoinColumn(name="prsn_city_id", on="ct_id")
	private CityDTO city;
	@JoinColumn(name="prsn_school_id", on="sch_id")
	private SchoolDTO school;
	@JoinColumn(name="prsn_country_id", on="cnt_id")
	private CountryDTO country;
	@JoinColumn(name="prsn_company_id", on="cmp_id")
	private CompanyDTO company;
	
	@JoinColumn(name="prsn_home_address_id", on="addr_id")
	private AddressDTO homeAddress;
	@JoinColumn(name="prsn_work_address_id", on="addr_id")
	private AddressDTO workAddress;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Character getGender() {
		return gender;
	}
	
	public void setGender(Character gender) {
		this.gender = gender;
	}
	
	public Short getAge() {
		return age;
	}
	
	public void setAge(Short age) {
		this.age = age;
	}
	
	public Integer getCpr() {
		return cpr;
	}
	
	public void setCpr(Integer cpr) {
		this.cpr = cpr;
	}
	
	public Long getAccountNo() {
		return accountNo;
	}
	
	public void setAccountNo(Long accountNo) {
		this.accountNo = accountNo;
	}
	
	public Float getGpa() {
		return gpa;
	}
	
	public void setGpa(Float gpa) {
		this.gpa = gpa;
	}
	
	public Double getSalary() {
		return salary;
	}
	
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	
	public BigDecimal getAnnualIncome() {
		return annualIncome;
	}
	
	public void setAnnualIncome(BigDecimal annualIncome) {
		this.annualIncome = annualIncome;
	}
	
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public Timestamp getRegistrationDateTime() {
		return registrationDateTime;
	}
	
	public void setRegistrationDateTime(Timestamp registrationDateTime) {
		this.registrationDateTime = registrationDateTime;
	}
	
	public Time getSleepTime() {
		return sleepTime;
	}
	
	public void setSleepTime(Time sleepTime) {
		this.sleepTime = sleepTime;
	}
	
	public Boolean getGraduated() {
		return graduated;
	}
	
	public void setGraduated(Boolean graduated) {
		this.graduated = graduated;
	}
	
	public List<String> getCertificates() {
		return certificates;
	}
	
	public void setCertificates(List<String> certificates) {
		this.certificates = certificates;
	}
	
	public CityDTO getCity() {
		return city;
	}
	
	public void setCity(CityDTO city) {
		this.city = city;
	}
	
	public SchoolDTO getSchool() {
		return school;
	}
	
	public void setSchool(SchoolDTO school) {
		this.school = school;
	}
	
	public CountryDTO getCountry() {
		return country;
	}
	
	public void setCountry(CountryDTO country) {
		this.country = country;
	}
	
	public CompanyDTO getCompany() {
		return company;
	}
	
	public void setCompany(CompanyDTO company) {
		this.company = company;
	}
	
	public AddressDTO getHomeAddress() {
		return homeAddress;
	}
	
	public void setHomeAddress(AddressDTO homeAddress) {
		this.homeAddress = homeAddress;
	}
	
	public AddressDTO getWorkAddress() {
		return workAddress;
	}
	
	public void setWorkAddress(AddressDTO workAddress) {
		this.workAddress = workAddress;
	}
}
