import by.maribo.web_service.CrawlingHandler;
import by.maribo.web_service.CrawlingService;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;

public class Main {
	private void start() {
		try {
			TServerSocket serverSocket = new TServerSocket(7911);
			CrawlingHandler handler = new CrawlingHandler();
			CrawlingService.Processor<CrawlingService.Iface> processor = new CrawlingService.Processor<>(handler);

			TServer tServer = new TThreadPoolServer(new TThreadPoolServer.Args(serverSocket).processor(processor));

			System.out.println("Start");
			tServer.serve();
		} catch (TTransportException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Main main = new Main();
		main.start();
	}
}
