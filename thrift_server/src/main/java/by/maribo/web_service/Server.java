package by.maribo.web_service;

import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Server {
	private static final Logger logger = LoggerFactory.getLogger(Server.class);

	public void start() {
		try {
			TServerSocket serverSocket = new TServerSocket(7911);
			HandbookHandler handler = new HandbookHandler();
			HandbookServer.Processor<HandbookServer.Iface> processor = new HandbookServer.Processor<>(handler);

			TServer tServer = new TThreadPoolServer(new TThreadPoolServer.Args(serverSocket).processor(processor));

			logger.info("Server started.");
			tServer.serve();
		} catch (TTransportException e) {
			logger.error(e.getMessage());
		}
	}
}