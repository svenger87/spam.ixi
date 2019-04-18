package org.iota.ixi;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.iota.ict.ixi.Ixi;
import org.iota.ict.ixi.IxiModule;
import org.iota.ict.model.transaction.Transaction;
import org.iota.ict.model.transaction.TransactionBuilder;
import org.iota.ict.network.gossip.GossipEvent;
import org.iota.ict.network.gossip.GossipListener;

import java.util.Random;

public class SpamIxi extends IxiModule {
	private static final Logger LOGGER = LogManager.getLogger("Spammer");

    public SpamIxi(Ixi ixi) {
        super(ixi);
    }

    public void run() {
		Random rand = new Random();
		while(isRunning()) {
			// Submit and print a random spam transaction
			String message = System.currentTimeMillis() + "spam" + rand.nextInt();
			TransactionBuilder builder = new TransactionBuilder();
			builder.asciiMessage(message);
			Transaction toSubmit = builder.build();
			ixi.submit(toSubmit);
			LOGGER.info("Sent random spam: " + message);
			
			// Sleep for 10 seconds
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				LOGGER.error("SpamModule encountered an error.", e);
			}
		}
    }
}

