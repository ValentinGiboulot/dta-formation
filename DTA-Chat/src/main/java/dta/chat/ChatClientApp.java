package dta.chat;

import java.util.Scanner;

import dta.chat.controller.ChatLoginStrategy;
import dta.chat.model.ChatConversationModel;
import dta.chat.view.console.ChatConsoleView;
import proxy.Proxy;

public class ChatClientApp {

	public static void main(String[] args) {

		try (Scanner read = new Scanner(System.in)) {

			Proxy connection = new Proxy("192.168.99.31", 1800);

			ChatConversationModel model = new ChatConversationModel(connection);

			final ChatConsoleView view = new ChatConsoleView(read);

			connection.afficherHistorique();

			view.setAuthController(new ChatLoginStrategy(model));

			model.addObserver(view);

			view.print();

			model.readMessage();

			while (true) {
				String s = read.nextLine();
				model.sendMessage(s);
			}

		}

	}

}
