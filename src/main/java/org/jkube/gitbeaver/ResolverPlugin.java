package org.jkube.gitbeaver;

import org.jkube.gitbeaver.interfaces.Command;
import org.jkube.gitbeaver.interfaces.Plugin;
import org.jkube.gitbeaver.plugin.SimplePlugin;

import java.util.List;

public class ResolverPlugin extends SimplePlugin {

    public ResolverPlugin() {
        super(ResolveCommand.class);
    }
    @Override
    public void init() {
        GitBeaver.setFileResolver(new RichFileResolver());
    }
}
