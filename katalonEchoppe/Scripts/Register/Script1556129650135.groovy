import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

WebUI.openBrowser('')

WebUI.navigateToUrl('http://localhost:9090/')

WebUI.click(findTestObject('Page_Echoppe/button_Accueil_navbar-toggler'))

WebUI.click(findTestObject('Page_Echoppe/a_Inscription'))

WebUI.setText(findTestObject('Page_Echoppe/input_Username_username'), 'usertest')

WebUI.setText(findTestObject('Page_Echoppe/input_Email address_email'), 'usertest@test.com')

WebUI.setEncryptedText(findTestObject('Page_Echoppe/input_Password_password'), '8SQVv/p9jVScEs4/2CZsLw==')

WebUI.setEncryptedText(findTestObject('Page_Echoppe/input_Confirm your password_inputPasswordConfirmation'), '8SQVv/p9jVScEs4/2CZsLw==')

WebUI.click(findTestObject('Page_Echoppe/button_Submit'))

