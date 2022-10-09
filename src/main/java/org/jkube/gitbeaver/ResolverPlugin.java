package org.jkube.gitbeaver;

import org.jkube.gitbeaver.interfaces.Command;
import org.jkube.gitbeaver.interfaces.Plugin;

import java.util.List;

public class ResolverPlugin implements Plugin {
    @Override
    public void init() {
        GitBeaver.setFileResolver(new RichFileResolver());
    }

    @Override
    public List<Command> getCommands() {
        return List.of(new ResolveCommand());
    }

    @Override
    public void shutdown() {
    }
}
