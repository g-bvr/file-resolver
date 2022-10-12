package org.jkube.gitbeaver.richfile.resolver;

import org.jkube.gitbeaver.DefaultFileResolver;
import org.jkube.gitbeaver.richfile.*;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

public class VariableResolver implements Resolver {

    private final Map<String, String> variables;

    public VariableResolver(Map<String, String> variables) {
        this.variables = variables;
    }

    @Override
    public boolean canResolve(String line) {
        return ResolveUtil.startsWith(line, Constants.SET);
    }

    @Override
    public List<LineInFile> resolve(LineInFile firstLine, List<LineInFile> remainingLines) {
        int indent = ResolveUtil.determineIndent(firstLine.line);
        setVariable(ResolveUtil.remainderOfCommand(firstLine.line, indent, Constants.SET));
        return remainingLines;
    }

    private void setVariable(final String setString) {
        if (setString == null) {
            throw new RuntimeException("Invalid emtpy set string");
        }
        String[] split = setString.trim().split(" ",2);
        if (split.length != 2) {
            throw new RuntimeException("Illegal syntax of SET: "+setString);
        }
        variables.put(split[0], split[1]);
    }

    public String substituteVariables(String line) {
        return DefaultFileResolver.substituteVariables(line ,variables);
    }
}
