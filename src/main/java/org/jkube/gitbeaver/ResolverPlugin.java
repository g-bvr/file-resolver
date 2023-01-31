package org.jkube.gitbeaver;

import org.jkube.gitbeaver.plugin.SimplePlugin;

public class ResolverPlugin extends SimplePlugin {

    public ResolverPlugin() {
        super(ResolveCommand.class);
    }
    @Override
    public void init() {
        GitBeaver.setFileResolver(new RichFileResolver());
    }
}
