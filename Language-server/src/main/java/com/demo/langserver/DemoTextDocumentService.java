package com.demo.langserver;

import org.eclipse.lsp4j.*;
import org.eclipse.lsp4j.jsonrpc.messages.Either;
import org.eclipse.lsp4j.services.TextDocumentService;

import com.demo.langserver.commons.LanguageServerContext;
import com.demo.langserver.core.LSClientLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class DemoTextDocumentService implements TextDocumentService {
   
	private final LanguageServerContext serverContext;
	private LSClientLogger clientLogger;
	
	public DemoTextDocumentService(LanguageServerContext serverContext) {
		this.serverContext = serverContext;
		this.clientLogger = LSClientLogger.getInstance(this.serverContext);
		clientLogger.logMessage("Demo DemoTextDocumentService has been instantiated ");
	}
	
	@Override
    public CompletableFuture<Either<List<CompletionItem>, CompletionList>> completion(CompletionParams completionParams) {
        
		clientLogger.logMessage("Demo TextDocumentService completion() "); //works
		
		// Provide completion item.
        return CompletableFuture.supplyAsync(() -> {
            List<CompletionItem> completionItems = new ArrayList<>();
            try {
                // Sample Completion item for sayHello
                CompletionItem completionItem = new CompletionItem();
                // Define the text to be inserted in to the file if the completion item is selected.
                completionItem.setInsertText("startDemo() {\n    print(\"Demo\")\n}");
                // Set the label that shows when the completion drop down appears in the Editor.
                completionItem.setLabel("startDemo()");
                // Set the completion kind. This is a snippet.
                // That means it replace character which trigger the completion and
                // replace it with what defined in inserted text.
                completionItem.setKind(CompletionItemKind.Snippet);
                // This will set the details for the snippet code which will help user to
                // understand what this completion item is.
                completionItem.setDetail("startDemo()\n this will show Demo");

                // Add the sample completion item to the list.
                completionItems.add(completionItem);
            } catch (Exception e) {
                //TODO: Handle the exception.
            }

            // Return the list of completion items.
            return Either.forLeft(completionItems);
        });
    }

    @Override
    public CompletableFuture<CompletionItem> resolveCompletionItem(CompletionItem completionItem) {
        return null;
    }

//    @Override
//    public CompletableFuture<Hover> hover(TextDocumentPositionParams textDocumentPositionParams) {
//        return null;
//    }
	@Override
	public CompletableFuture<Hover> hover(HoverParams params) {

		return TextDocumentService.super.hover(params);
	}
    
//    @Override
//    public CompletableFuture<SignatureHelp> signatureHelp(TextDocumentPositionParams textDocumentPositionParams) {
//        return null;
//    }
	@Override
	public CompletableFuture<SignatureHelp> signatureHelp(SignatureHelpParams params) {

		return TextDocumentService.super.signatureHelp(params);
	}	
    	
//    @Override
//    public CompletableFuture<List<? extends Location>> definition(TextDocumentPositionParams textDocumentPositionParams) {
//        return null;
//    }
	@Override
	public CompletableFuture<Either<List<? extends Location>, List<? extends LocationLink>>> definition(
			DefinitionParams params) {

		return TextDocumentService.super.definition(params);
	}
    	
    @Override
    public CompletableFuture<List<? extends Location>> references(ReferenceParams referenceParams) {
        return null;
    }

//    @Override
//    public CompletableFuture<List<? extends DocumentHighlight>> documentHighlight(TextDocumentPositionParams textDocumentPositionParams) {
//        return null;
//    }
	@Override
	public CompletableFuture<List<? extends DocumentHighlight>> documentHighlight(DocumentHighlightParams params) {

		return TextDocumentService.super.documentHighlight(params);
	}

//    @Override
//    public CompletableFuture<List<? extends SymbolInformation>> documentSymbol(DocumentSymbolParams documentSymbolParams) {
//        return null;
//    }
	@Override
	public CompletableFuture<List<Either<SymbolInformation, DocumentSymbol>>> documentSymbol(
			DocumentSymbolParams params) {

		return TextDocumentService.super.documentSymbol(params);
	}
    	
//    @Override
//    public CompletableFuture<List<? extends Command>> codeAction(CodeActionParams codeActionParams) {
//        return null;
//    }
	@Override
	public CompletableFuture<List<Either<Command, CodeAction>>> codeAction(CodeActionParams params) {

		return TextDocumentService.super.codeAction(params);
	}
    	
    @Override
    public CompletableFuture<List<? extends CodeLens>> codeLens(CodeLensParams codeLensParams) {
        return null;
    }

    @Override
    public CompletableFuture<CodeLens> resolveCodeLens(CodeLens codeLens) {
        return null;
    }

    @Override
    public CompletableFuture<List<? extends TextEdit>> formatting(DocumentFormattingParams documentFormattingParams) {
        return null;
    }

    @Override
    public CompletableFuture<List<? extends TextEdit>> rangeFormatting(DocumentRangeFormattingParams documentRangeFormattingParams) {
        return null;
    }

    @Override
    public CompletableFuture<List<? extends TextEdit>> onTypeFormatting(DocumentOnTypeFormattingParams documentOnTypeFormattingParams) {
        return null;
    }

    @Override
    public CompletableFuture<WorkspaceEdit> rename(RenameParams renameParams) {
        return null;
    }

    @Override
    public void didOpen(DidOpenTextDocumentParams didOpenTextDocumentParams) {

    }

    @Override
    public void didChange(DidChangeTextDocumentParams didChangeTextDocumentParams) {

    }

    @Override
    public void didClose(DidCloseTextDocumentParams didCloseTextDocumentParams) {

    }

    @Override
    public void didSave(DidSaveTextDocumentParams didSaveTextDocumentParams) {

    }
}
