"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.deactivate = exports.activate = void 0;
// The module 'vscode' contains the VS Code extensibility API
// Import the module and reference it with the alias vscode in your code below
const path = require("path");
const vscode = require("vscode");
// Import the language client, language client options and server options from VSCode language client.
const vscode_languageclient_1 = require("vscode-languageclient");
// Name of the launcher class which contains the main.
const main = 'StdioLauncher';
// this method is called when your extension is activated
// your extension is activated the very first time the command is executed
function activate(context) {
    // Use the console to output diagnostic information (console.log) and errors (console.error)
    // This line of code will only be executed once when your extension is activated
    console.log('Congratulations, your extension "VsCode Extension Demo" is now active!');
    console.log('::: Activating your extension...');
    // Get the java home from the process environment.
    const { JAVA_HOME } = process.env;
    console.log(`::: Using java from JAVA_HOME: ${JAVA_HOME}`);
    // If java home is available continue.
    if (JAVA_HOME) {
        // Java execution path.
        const command = path.join(JAVA_HOME, 'bin', 'java');
        // With initial server in Java:
        // VsCodeTraining\Launcher\target\launcher.jar
        let classPath = path.join(__dirname, '..', '..', '.', 'Launcher', 'target', 'launcher.jar');
        console.log(`::: Using classPath = ${classPath}`);
        const args = ['-cp', classPath, 'StdioLauncher'];
        // Set the server options
        let serverOptions = {
            command,
            args,
            options: {}
        };
        // Options to control the language client
        let clientOptions = {
            // Register the server for plain text documents
            documentSelector: [{ scheme: 'file', language: 'demo' }]
        };
        // Create the language client and start the client.
        let disposable = new vscode_languageclient_1.LanguageClient('Language-server', 'Demo Language Server', serverOptions, clientOptions).start();
        // Disposables to remove on deactivation.
        context.subscriptions.push(disposable);
    }
    // The command has been defined in the package.json file
    // Now provide the implementation of the command with registerCommand
    // The commandId parameter must match the command field in package.json
    let disposable = vscode.commands.registerCommand('vscodedemo.helloWorld', () => {
        // The code you place here will be executed every time your command is executed
        // Display a message box to the user
        vscode.window.showInformationMessage('Hello Folks from Lidia!');
    });
    context.subscriptions.push(disposable);
    let disposable2 = vscode.commands.registerCommand('vscodedemo.currentTime', () => {
        // The code you place here will be executed every time your command is executed
        // Display current time & date
        vscode.window.showInformationMessage('Now: ' + new Date().getHours() + ":" + new Date().getMinutes() + " " + new Date().toLocaleDateString());
    });
    context.subscriptions.push(disposable2);
}
exports.activate = activate;
// this method is called when your extension is deactivated
function deactivate() {
    console.log('Your extension "VsCode Extension Demo" is now deactivated!');
}
exports.deactivate = deactivate;
//# sourceMappingURL=extension.js.map