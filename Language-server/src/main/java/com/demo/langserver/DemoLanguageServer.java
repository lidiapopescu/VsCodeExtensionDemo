package com.demo.langserver;

import org.eclipse.lsp4j.CompletionOptions;
import org.eclipse.lsp4j.InitializeParams;
import org.eclipse.lsp4j.InitializeResult;
import org.eclipse.lsp4j.ServerCapabilities;
import org.eclipse.lsp4j.TextDocumentSyncKind;
import org.eclipse.lsp4j.services.LanguageClient;
import org.eclipse.lsp4j.services.LanguageClientAware;
import org.eclipse.lsp4j.services.LanguageServer;
import org.eclipse.lsp4j.services.TextDocumentService;
import org.eclipse.lsp4j.services.WorkspaceService;

import com.demo.langserver.commons.LanguageServerContext;
import com.demo.langserver.core.LSClientLogger;
import com.demo.langserver.core.context.LanguageServerContextImpl;

import java.util.concurrent.CompletableFuture;

public class DemoLanguageServer implements LanguageServer, LanguageClientAware {
    
	private final TextDocumentService textDocumentService;
    private final WorkspaceService workspaceService;
    private final LanguageServerContext serverContext;
    private LanguageClient client;
    
    private int errorCode = 1;
    private LSClientLogger clientLogger;
    
    public DemoLanguageServer() {
    	this(new LanguageServerContextImpl());
    }

    public DemoLanguageServer(LanguageServerContext serverContext) {
    	this.serverContext = serverContext;
        this.textDocumentService = new DemoTextDocumentService(this.serverContext);
        this.workspaceService = new DemoWorkspaceService(this.serverContext);
        this.clientLogger = LSClientLogger.getInstance(this.serverContext);
		clientLogger.logMessage("Demo LanguageServer has been instantiated "); // doesn't show?
    }
    
    @Override
    public CompletableFuture<InitializeResult> initialize(InitializeParams initializeParams) {
        // Initialize the InitializeResult for this LS.
        final InitializeResult initializeResult = new InitializeResult(new ServerCapabilities());

        // Set the capabilities of the LS to inform the client.
        initializeResult.getCapabilities().setTextDocumentSync(TextDocumentSyncKind.Full);
        CompletionOptions completionOptions = new CompletionOptions();
        initializeResult.getCapabilities().setCompletionProvider(completionOptions);
        
        clientLogger.logMessage("Demo LanguageServer initialize()"); //works
        return CompletableFuture.supplyAsync(()->initializeResult);
    }

    
    @Override
    public void initialized() {
    	clientLogger.logMessage("Demo LanguageServer initialized() ");// works
//    	LanguageServer.super.initialized();
    }
    @Override
    public CompletableFuture<Object> shutdown() {
        // If shutdown request comes from client, set the error code to 0.
        errorCode = 0;
        return CompletableFuture.supplyAsync(Object::new);
    }

    @Override
    public void exit() {
        // Kill the LS on exit request from client.
        System.exit(errorCode);
    }

    @Override
    public TextDocumentService getTextDocumentService() {
        // Return the endpoint for language features.
        return this.textDocumentService;
    }

    @Override
    public WorkspaceService getWorkspaceService() {
        // Return the endpoint for workspace functionality.
        return this.workspaceService;
    }

    @Override
    public void connect(LanguageClient languageClient) {
        // Get the client which started this LS.
        this.client = languageClient;
//        this.serverContext.setClient(this.client);
        this.clientLogger = LSClientLogger.getInstance(this.serverContext);
    	this.clientLogger.initialize(this.client, this.serverContext);
    }
}
