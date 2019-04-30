import com.kms.katalon.core.logging.KeywordLogger
import com.kms.katalon.core.exception.StepFailedException
import com.kms.katalon.core.reporting.ReportUtil
import com.kms.katalon.core.main.TestCaseMain
import com.kms.katalon.core.testdata.TestDataColumn
import com.kms.katalon.core.testcase.TestCaseBinding
import com.kms.katalon.core.driver.internal.DriverCleanerCollector
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.configuration.RunConfiguration
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import internal.GlobalVariable as GlobalVariable

Map<String, String> suiteProperties = new HashMap<String, String>();


suiteProperties.put('id', 'Test Suites/New Test Suite')

suiteProperties.put('name', 'New Test Suite')

suiteProperties.put('description', '')
 

DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.webui.contribution.WebUiDriverCleaner())
DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.mobile.contribution.MobileDriverCleaner())
DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.cucumber.keyword.internal.CucumberDriverCleaner())



RunConfiguration.setExecutionSettingFile("C:\\Users\\quentin.michel\\Documents\\hearc\\ANNEE-3\\Java_Entreprise\\Projet\\echoppe\\katalonEchoppe\\Reports\\New Test Suite\\20190501_000136\\execution.properties")

TestCaseMain.beforeStart()

TestCaseMain.startTestSuite('Test Suites/New Test Suite', suiteProperties, [new TestCaseBinding('Test Cases/Register', 'Test Cases/Register',  null), new TestCaseBinding('Test Cases/Logout', 'Test Cases/Logout',  null), new TestCaseBinding('Test Cases/Login', 'Test Cases/Login',  null), new TestCaseBinding('Test Cases/List Article', 'Test Cases/List Article',  null), new TestCaseBinding('Test Cases/Search Article', 'Test Cases/Search Article',  null), new TestCaseBinding('Test Cases/Article Detail', 'Test Cases/Article Detail',  null), new TestCaseBinding('Test Cases/Comment Article', 'Test Cases/Comment Article',  null)])
