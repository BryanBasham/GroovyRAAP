import org.raap.domain.user.Role
import org.raap.domain.user.User
import org.raap.domain.user.Requestmap

import org.raap.domain.*

class BootStrap {
	
	// Injected by Spring
	def authenticateService
	
	def init = { servletContext ->
		// Create basic security data
		if ( Role.count() == 0 ) {
			_createRoles()
			_createUsers()
		}
		
		// Create basic/static domain data
		if ( County.count() == 0 ) {
			_createCounties()
			_createCrimeTypes()
			_createEthnicities()
			_createGenders()
			_createIncomeLevels()
			_createLanguages()
			_createSexualOrientations()
			_createServiceTypes()
		}
	}
	def destroy = {
	}
	
	//
	// Private methods
	//
	
	private ROLE_USER
	private ROLE_ADMIN
	private ROLE_CASE_MANAGER
	private ROLE_COUNSELOR
	private ROLE_CLINICAL_SUPERVISOR
	private ROLE_INTAKE_COORDINATOR
	private _createRoles() {
		ROLE_USER = new Role(authority:'ROLE_USER',
				description:'An generic user (RAAP staff) that has limited authorities.').save();
		ROLE_ADMIN = new Role(authority:'ROLE_ADMIN',
				description:'An administrator of the RAAP Client Services CMS webapp.').save();
		ROLE_CASE_MANAGER = new Role(authority:'ROLE_CASE_MANAGER',
				description:'A Case Manager provides referrals to social services for RAAP clients.').save();
		ROLE_COUNSELOR = new Role(authority:'ROLE_COUNSELOR',
				description:'A Counselor provides pyshcotherapy services for RAAP clients.').save();
		ROLE_CLINICAL_SUPERVISOR = new Role(authority:'ROLE_CLINICAL_SUPERVISOR',
				description:'A Clinical Supervisor provides supervision to counseling staff.').save();
		ROLE_INTAKE_COORDINATOR = new Role(authority:'ROLE_INTAKE_COORDINATOR',
				description:'The Intake Coordinator is a counselor that manages the intake line.').save();
	}
	private _createUsers() {
		def NO = new User(username:'NO_ONE', userRealName:'NO ONE',
				passwd: authenticateService.encodePassword('B0gUs'),
				enabled: false, email: 'noOne@noWhere.NET', emailShow:false,
				supervisor: null).save();
		def BB = new User(username:'BBasham', userRealName:'Bryan Basham',
				passwd: authenticateService.encodePassword('(RAAP47)'),
				enabled: true, email: 'basham47@gmail.com', emailShow: true,
				supervisor: NO).addToAuthorities(ROLE_ADMIN).save();
		def TM = new User(username:'TManzione', userRealName:'Tom Manzione',
				passwd: authenticateService.encodePassword('T.M'),
				enabled: true, email: 'TManzione@raap.org', emailShow: true,
				supervisor: NO).addToAuthorities(ROLE_USER).save();
		def KCr = new User(username:'KCreedon', userRealName:'Katie Creedon',
				passwd: authenticateService.encodePassword('K.C'),
				enabled: true, email: 'KCreedon@raap.org', emailShow: true,
				supervisor: TM).addToAuthorities(ROLE_USER).save();
		def KC = new User(username:'KCollina', userRealName:'Kathy Collina',
				passwd: authenticateService.encodePassword('K.C'),
				enabled: true, email: 'KCollina@raap.org', emailShow: true,
				supervisor: KCr).addToAuthorities(ROLE_COUNSELOR)
				.addToAuthorities(ROLE_CLINICAL_SUPERVISOR).save();
		def SM = new User(username:'SMerchant', userRealName:'Shayeestah Merchant',
				passwd: authenticateService.encodePassword('S.M'),
				enabled: true, email: 'SMerchant@raap.org', emailShow: true,
				supervisor: KC).addToAuthorities(ROLE_COUNSELOR)
				.addToAuthorities(ROLE_INTAKE_COORDINATOR).save();
		def RP = new User(username:'RPearl', userRealName:'Reaca Pearl',
				passwd: authenticateService.encodePassword('R.P'),
				enabled: true, email: 'RPearl@raap.org', emailShow: true,
				supervisor: KC).addToAuthorities(ROLE_COUNSELOR).save();
	}
	
	private _createCounties() {
		log.info "_createCounties"
		new County(dbCode:'UN', name:'Unknown').save();
		new County(dbCode:'AD', name:'Adams').save();
		new County(dbCode:'AR', name:'Arapahoe').save();
		new County(dbCode:'BR', name:'Broomfield').save();
		new County(dbCode:'DE', name:'Denver').save();
		new County(dbCode:'DO', name:'Douglas').save();
		new County(dbCode:'EL', name:'Elbert').save();
		new County(dbCode:'JE', name:'Jefferson').save();
		new County(dbCode:'LI', name:'Lincoln').save();
		new County(dbCode:'OT', name:'Other').save();
	}
	
	private _createCrimeTypes() {
		log.info "_createCrimeTypes"
		new CrimeType(dbCode:'UN', name:'Unknown').save();
		new CrimeType(dbCode:'CSA', name:'Child Sexual Abuse').save();
		new CrimeType(dbCode:'ASA', name:'Adult Sexual Assault').save();
		new CrimeType(dbCode:'AMC', name:'Adult Molested as a Child').save();
		new CrimeType(dbCode:'CPA', name:'Child Physical Abuse').save();
		new CrimeType(dbCode:'DOV', name:'Domestic Violence').save();
		new CrimeType(dbCode:'ELA', name:'Elder Abuse').save();
	}
	
	private _createEthnicities() {
		log.info "_createEthnicities"
		new Ethnicity(dbCode:'UN', name:'Unknown').save();
		new Ethnicity(dbCode:'CA', name:'Caucasian').save();
		new Ethnicity(dbCode:'LA', name:'Latina/o').save();
		new Ethnicity(dbCode:'AS', name:'Asian').save();
		new Ethnicity(dbCode:'AA', name:'African American').save();
		new Ethnicity(dbCode:'AI', name:'American Indian').save();
		new Ethnicity(dbCode:'AN', name:'Alaskan Native').save();
		new Ethnicity(dbCode:'PI', name:'Pacific Islander').save();
		new Ethnicity(dbCode:'OT', name:'Other').save();
	}
	
	private _createGenders() {
		log.info "_createGenders"
		new Gender(dbCode:'UN', name:'Unknown').save();
		new Gender(dbCode:'FE', name:'Female').save();
		new Gender(dbCode:'MA', name:'Male').save();
		new Gender(dbCode:'TG', name:'Transgendered').save();
		new Gender(dbCode:'TS', name:'Transexual').save();
	}
	
	private _createIncomeLevels() {
		log.info "_createIncomeLevels"
		new IncomeLevel(dbCode:'UN', range:'Unknown').save();
		new IncomeLevel(dbCode:'01', range:'up to $4,999').save();
		new IncomeLevel(dbCode:'02', range:'$5,000 to $9,999').save();
		new IncomeLevel(dbCode:'03', range:'$10,000 to $14,999').save();
		new IncomeLevel(dbCode:'04', range:'$15,000 to $19,999').save();
		new IncomeLevel(dbCode:'05', range:'$20,000 to $24,999').save();
		new IncomeLevel(dbCode:'06', range:'$25,000 to $29,999').save();
		new IncomeLevel(dbCode:'07', range:'$30,000 to $34,999').save();
		new IncomeLevel(dbCode:'08', range:'$35,000 to $39,999').save();
		new IncomeLevel(dbCode:'09', range:'$40,000 to $44,999').save();
		new IncomeLevel(dbCode:'10', range:'$45,000 to $59,999').save();
		new IncomeLevel(dbCode:'11', range:'$50,000 and up').save();
	}
	
	private _createLanguages() {
		log.info "_createLanguages"
		new Language(dbCode:'EN', name:'English').save();
		new Language(dbCode:'ES', name:'Spanish').save();
		new Language(dbCode:'DE', name:'German').save();
		new Language(dbCode:'KO', name:'Korean').save();
		new Language(dbCode:'JP', name:'Japanese').save();
		new Language(dbCode:'OT', name:'Other').save();
	}

	private _createSexualOrientations() {
		log.info "_createSexualOrientations"
		new Sexuality(dbCode:'UN', name:'Unknown').save();
		new Sexuality(dbCode:'HE', name:'Heterosexual').save();
		new Sexuality(dbCode:'GA', name:'Gay').save();
		new Sexuality(dbCode:'LE', name:'Lesbian').save();
		new Sexuality(dbCode:'BI', name:'Bisexual').save();
	}

	private _createServiceTypes() {
		log.info "_createServiceTypes"
		new ServiceType(dbCode:'I', name:'Phone Intake', defaultDuration:15).save();
		new ServiceType(dbCode:'IA', name:'Individual Assessment', defaultDuration:60).save();
		new ServiceType(dbCode:'CC', name:'Crisis Counseling', defaultDuration:30).save();
		new ServiceType(dbCode:'IC', name:'Individual Counseling', defaultDuration:60).save();
		new ServiceType(dbCode:'GT', name:'Group Treatment/Support', defaultDuration:120).save();
		new ServiceType(dbCode:'FU', name:'Follow Up', defaultDuration:10).save();
		new ServiceType(dbCode:'VC', name:'Inform/Assist Filing VC', defaultDuration:10).save();
		new ServiceType(dbCode:'IR', name:'Info/Referral (in person)', defaultDuration:15).save();
		new ServiceType(dbCode:'TC', name:'Telephone Contact', defaultDuration:5).save();
		new ServiceType(dbCode:'PA', name:'Personal Advocacy', defaultDuration:60).save();
	}
	
} 