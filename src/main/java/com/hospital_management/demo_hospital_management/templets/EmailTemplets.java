package com.hospital_management.demo_hospital_management.templets;

import org.springframework.stereotype.Service;

@Service
public class EmailTemplets {
	
	public String getRegistrationSuccessTemplate(String hospitalName, String altUsername, String altPassword, String role, Long id) {
	    return "<!DOCTYPE html>" +
	           "<html>" +
	           "<head>" +
	           "<style>" +
	           "body {" +
	           "    font-family: Arial, sans-serif;" +
	           "    margin: 0;" +
	           "    padding: 0;" +
	           "    background-color: #f4f4f9;" +
	           "}" +
	           ".container {" +
	           "    width: 100%;" +
	           "    max-width: 600px;" +
	           "    margin: 20px auto;" +
	           "    background: #ffffff;" +
	           "    border-radius: 10px;" +
	           "    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);" +
	           "    overflow: hidden;" +
	           "}" +
	           ".header {" +
	           "    background: linear-gradient(135deg, #42a5f5, #0d47a1);" +
	           "    color: #ffffff;" +
	           "    text-align: center;" +
	           "    padding: 20px;" +
	           "    font-size: 24px;" +
	           "}" +
	           ".body {" +
	           "    padding: 20px;" +
	           "    line-height: 1.6;" +
	           "    color: #333333;" +
	           "}" +
	           ".footer {" +
	           "    background-color: #f4f4f9;" +
	           "    color: #888888;" +
	           "    text-align: center;" +
	           "    padding: 10px;" +
	           "    font-size: 12px;" +
	           "}" +
	           "</style>" +
	           "</head>" +
	           "<body>" +
	           "<div class=\"container\">" +
	           "<div class=\"header\">" +
	           "Welcome to " + hospitalName +
	           "</div>" +
	           "<div class=\"body\">" +
	           "<p>Dear User,</p>" +
	           "<p>Your registration for the " + hospitalName + " platform has been successfully completed!</p>" +
	           "<p>Here are your secure login details:</p>" +
	           "<p><strong>Login ID:</strong> " + altUsername + "</p>" +
	           "<p><strong>Access Key:</strong> " + altPassword + "</p>" +
	           "<p><strong>Role:</strong> " + role + "</p>" +
	           "<p><strong>Associate ID:</strong> " + id + "</p>" +
	           "<p>Please use these details to log in and change your password as soon as possible for added security.</p>" +
	           "</div>" +
	           "<div class=\"footer\">" +
	           "&copy; 2024 " + hospitalName + ". All Rights Reserved." +
	           "</div>" +
	           "</div>" +
	           "</body>" +
	           "</html>";
	}

	
	public String getUserDeactivationTemplate(String username, String hospitalName) {
	    return "<!DOCTYPE html>" +
	           "<html>" +
	           "<head>" +
	           "<style>" +
	           "body {" +
	           "    font-family: Arial, sans-serif;" +
	           "    margin: 0;" +
	           "    padding: 0;" +
	           "    background-color: #f4f4f9;" +
	           "}" +
	           ".container {" +
	           "    width: 100%;" +
	           "    max-width: 600px;" +
	           "    margin: 20px auto;" +
	           "    background: #ffffff;" +
	           "    border-radius: 10px;" +
	           "    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);" +
	           "    overflow: hidden;" +
	           "}" +
	           ".header {" +
	           "    background: linear-gradient(135deg, #42a5f5, #0d47a1);" +
	           "    color: #ffffff;" +
	           "    text-align: center;" +
	           "    padding: 20px;" +
	           "    font-size: 24px;" +
	           "}" +
	           ".body {" +
	           "    padding: 20px;" +
	           "    line-height: 1.6;" +
	           "    color: #333333;" +
	           "}" +
	           ".footer {" +
	           "    background-color: #f4f4f9;" +
	           "    color: #888888;" +
	           "    text-align: center;" +
	           "    padding: 10px;" +
	           "    font-size: 12px;" +
	           "}" +	           "</style>" +
	           "</head>" +
	           "<body>" +
	           "<div class=\"container\">" +
	           "<div class=\"header\">" +
	           "Account Deactivated" +
	           "</div>" +
	           "<div class=\"body\">" +
	           "<p>Dear " + username + ",</p>" +
	           "<p>Your account has been marked as inactive.</p>" +
	           "<p>This could be due to one of the following reasons:</p>" +
	           "<ul>" +
	           "<li>You chose to deactivate your account.</li>" +
	           "<li>An administrator deactivated your account.</li>" +
	           "</ul>" +
	           "<p>If you believe this is an error, please contact support.</p>" +
	           "</div>" +
	           "<div class=\"footer\">" +
	           "&copy; 2024 " + hospitalName + ". All Rights Reserved." +
	           "</div>" +
	           "</div>" +
	           "</body>" +
	           "</html>";
	}

	public String getUserReactivationTemplate(String username, String hospitalName) {
	    return "<!DOCTYPE html>" +
	           "<html>" +
	           "<head>" +
	           "<style>" +
	           "body {" +
	           "    font-family: Arial, sans-serif;" +
	           "    margin: 0;" +
	           "    padding: 0;" +
	           "    background-color: #f4f4f9;" +
	           "}" +
	           ".container {" +
	           "    width: 100%;" +
	           "    max-width: 600px;" +
	           "    margin: 20px auto;" +
	           "    background: #ffffff;" +
	           "    border-radius: 10px;" +
	           "    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);" +
	           "    overflow: hidden;" +
	           "}" +
	           ".header {" +
	           "    background: linear-gradient(135deg, #42a5f5, #0d47a1);" +
	           "    color: #ffffff;" +
	           "    text-align: center;" +
	           "    padding: 20px;" +
	           "    font-size: 24px;" +
	           "}" +
	           ".body {" +
	           "    padding: 20px;" +
	           "    line-height: 1.6;" +
	           "    color: #333333;" +
	           "}" +
	           ".footer {" +
	           "    background-color: #f4f4f9;" +
	           "    color: #888888;" +
	           "    text-align: center;" +
	           "    padding: 10px;" +
	           "    font-size: 12px;" +
	           "}" +	           "</style>" +
	           "</head>" +
	           "<body>" +
	           "<div class=\"container\">" +
	           "<div class=\"header\">" +
	           "Account Reactivated" +
	           "</div>" +
	           "<div class=\"body\">" +
	           "<p>Dear " + username + ",</p>" +
	           "<p>We are pleased to inform you that your account has been reactivated.</p>" +
	           "<p>You can now log in and continue using the application.</p>" +
	           "<p>If you have any questions, feel free to reach out to our support team.</p>" +
	           "</div>" +
	           "<div class=\"footer\">" +
	           "&copy; 2024 " + hospitalName + ". All Rights Reserved." +
	           "</div>" +
	           "</div>" +
	           "</body>" +
	           "</html>";
	}


}
