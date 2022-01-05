package com.demo.langserver;

import org.eclipse.lsp4j.DidChangeConfigurationParams;
import org.eclipse.lsp4j.DidChangeWatchedFilesParams;
import org.eclipse.lsp4j.SymbolInformation;
import org.eclipse.lsp4j.WorkspaceSymbolParams;
import org.eclipse.lsp4j.services.WorkspaceService;

import com.demo.langserver.commons.LanguageServerContext;
import com.demo.langserver.core.LSClientLogger;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class DemoWorkspaceService implements WorkspaceService {
    
	private final LSClientLogger clientLogger;
	private final LanguageServerContext serverContext;
	
	
	public DemoWorkspaceService(LanguageServerContext serverContext) {
		this.serverContext = serverContext;
		this.clientLogger = LSClientLogger.getInstance(serverContext);
		clientLogger.logMessage("Demo DemoWorkspaceService has been instantiated");
	}
	
	@Override
    public CompletableFuture<List<? extends SymbolInformation>> symbol(WorkspaceSymbolParams workspaceSymbolParams) {
        return null;
    }

    @Override
    public void didChangeConfiguration(DidChangeConfigurationParams didChangeConfigurationParams) {

    }

    @Override
    public void didChangeWatchedFiles(DidChangeWatchedFilesParams didChangeWatchedFilesParams) {

    }
}
