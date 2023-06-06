package org.jkube.gitbeaver;

import org.jkube.gitbeaver.plugin.SimplePlugin;

public class ResolverPlugin extends SimplePlugin {

    public ResolverPlugin() {
        super("Resolves text files by substituting variables, expanding macros and evaluate conditional sections",
                ResolveAppendCommand.class,
                ResolveCommand.class);
    }
    @Override
    public void init() {
        GitBeaver.setFileResolver(new RichFileResolver());
    }
}
