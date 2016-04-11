package com.example.mockdemo.app;

import static org.junit.Assert.assertEquals;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import com.example.mockdemo.messenger.MessageServiceSimpleImpl;

public class MessengerSteps {

	private Messenger msg;
	private String serverToSend;
	private String messageToSend;
	
	@Given("a messenger")
	public void MessengerSetup(){
		msg = new Messenger(new MessageServiceSimpleImpl());
	}
	
	@When("set arguments serverToSend to $serverToSend and messageToSend to $messageToSend")
	public void setArguments(String serverToSend, String messageToSend){
		this.serverToSend = serverToSend;
		this.messageToSend = messageToSend;
	}
	
    @Then("sendValidS should return $result")
	public void sendValidS(int result){;
		assertEquals(result, msg.sendMessage(serverToSend, messageToSend));
	}

}
