package com.llh.servlet;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AsyncServlet", urlPatterns = { "/async.do" }, asyncSupported = true)
public class AsyncServlet extends HttpServlet {
	// 执行绪池
	private ExecutorService executorService = Executors.newFixedThreadPool(10);
	private AtomicLong count = new AtomicLong(0);

    public AtomicLong getCount() {
        return count;
    }

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF8");
		AsyncContext ctx = request.startAsync();
		//count.incrementAndGet();
        System.out.println(count.incrementAndGet());
		executorService.submit(new AsyncRequest(ctx));
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	public void destroy() {
		executorService.shutdown();
	}
}
