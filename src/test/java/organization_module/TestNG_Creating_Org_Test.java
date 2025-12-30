package organization_module;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import ObjectRepo.Creating_New_Organization_Page;
import ObjectRepo.HomePage;
import ObjectRepo.OrganizationPage;
import ObjectRepo.Organization_Verification_Msg_Page;
import generic_libraries.BaseClass;

@Listeners(generic_libraries.ListenerImplementationClass.class)
public class TestNG_Creating_Org_Test extends BaseClass{
	
	@Test(groups = "Organization",retryAnalyzer = generic_libraries.RetryImplementationClass.class)
	public void Creating_Organization() throws EncryptedDocumentException, IOException {
//		Click on 'ORGANIZATIONS' link

		HomePage hp = new HomePage(driver);
		hp.clickOnOrganizationsLink();

		//		Click on 'ORGANIZATIONS'  lookup image

		OrganizationPage op = new OrganizationPage(driver);
		op.clickonOrganizationLookUp();
		
		// Reading data from excel
		String orgName=eLib.readDataFromExcel("Organizations", 0, 1)+"_"+jLib.getRandom();
		System.out.println("orgName = "+orgName);
		String industryName=eLib.readDataFromExcel("Organizations", 1, 1);
		System.out.println("industryName = "+industryName);
		String typeName=eLib.readDataFromExcel("Organizations", 2, 1);
		System.out.println("typeName = "+typeName);
		
		Creating_New_Organization_Page cop = new Creating_New_Organization_Page(driver);
		cop.createOrganization(orgName, industryName, typeName);

		Organization_Verification_Msg_Page m = new Organization_Verification_Msg_Page(driver);
		m.verifyOrgCreated(orgName);
		
		System.out.println("======================Creating_Org_Complete 1 =======================");

	}
}
