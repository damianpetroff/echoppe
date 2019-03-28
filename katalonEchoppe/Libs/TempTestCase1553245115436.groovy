import com.kms.katalon.core.main.TestCaseMain
import com.kms.katalon.core.logging.KeywordLogger
import com.kms.katalon.core.testcase.TestCaseBinding
import com.kms.katalon.core.driver.internal.DriverCleanerCollector
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.webui.contribution.WebUiDriverCleaner
import com.kms.katalon.core.mobile.contribution.MobileDriverCleaner
import com.kms.katalon.core.cucumber.keyword.internal.CucumberDriverCleaner


DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.webui.contribution.WebUiDriverCleaner())
DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.mobile.contribution.MobileDriverCleaner())
DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.cucumber.keyword.internal.CucumberDriverCleaner())


RunConfiguration.setExecutionSettingFile('C:\\Users\\DAMIAN~1.PET\\AppData\\Local\\Temp\\Katalon\\20190322_095830\\execution.properties')

TestCaseMain.beforeStart()

        TestCaseMain.runWSVerificationScript(new TestCaseBinding('',[:]), 'import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI\nimport com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile\nimport com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW\nimport com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS\nimport static com.kms.katalon.core.testobject.ObjectRepository.findTestObject\nimport static com.kms.katalon.core.testdata.TestDataFactory.findTestData\nimport static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase\nimport static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint\nimport com.kms.katalon.core.model.FailureHandling as FailureHandling\nimport com.kms.katalon.core.testcase.TestCase as TestCase\nimport com.kms.katalon.core.testdata.TestData as TestData\nimport com.kms.katalon.core.testobject.TestObject as TestObject\nimport com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint\nimport internal.GlobalVariable as GlobalVariable\nimport org.openqa.selenium.Keys as Keys\n\nWebUI.openBrowser(\'\')\n\nWebUI.navigateToUrl(\'http://localhost:9090/\')\n\nWebUI.click(findTestObject(\'Page_Spring Boot Thymeleaf/a_Accueil\'))\n\nWebUI.click(findTestObject(\'Page_Spring Boot Thymeleaf/span_Accueil_navbar-toggler-icon\'))\n\nWebUI.click(findTestObject(\'Page_Spring Boot Thymeleaf/a_Liste products\'))\n\nWebUI.click(findTestObject(\'Page_Spring Boot Thymeleaf/a_Nouveau produit\'))\n\nWebUI.setText(findTestObject(\'Page_Spring Boot Thymeleaf/input_nom du produit_name\'), \'abc\')\n\nWebUI.setText(findTestObject(\'Page_Spring Boot Thymeleaf/input_prix du produit_price\'), \'32\')\n\nWebUI.click(findTestObject(\'Page_Spring Boot Thymeleaf/button_Sauvegarder\'))\n\nWebUI.click(findTestObject(\'Page_Spring Boot Thymeleaf/td_abc\'))\n\nWebUI.click(findTestObject(\'Page_Spring Boot Thymeleaf/td_3200\'))\n\nWebUI.click(findTestObject(\'Page_Spring Boot Thymeleaf/span_Accueil_navbar-toggler-icon\'))\n\nWebUI.click(findTestObject(\'Page_Spring Boot Thymeleaf/a_Saisie products\'))\n\nWebUI.click(findTestObject(\'Page_Spring Boot Thymeleaf/span_Accueil_navbar-toggler-icon\'))\n\nWebUI.click(findTestObject(\'Page_Spring Boot Thymeleaf/a_Menu\'))\n\nWebUI.closeBrowser()\n\n', FailureHandling.STOP_ON_FAILURE, true)

