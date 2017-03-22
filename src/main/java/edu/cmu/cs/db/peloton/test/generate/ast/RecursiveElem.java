package edu.cmu.cs.db.peloton.test.generate.ast;

import com.google.common.collect.ImmutableList;
import edu.cmu.cs.db.peloton.test.generate.Context;

import java.util.Iterator;
import java.util.List;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by tianyuli on 3/22/17.
 */
public abstract class RecursiveElem extends ProductElem {
    protected abstract int numChildren();

    protected abstract Iterator<Ast.Clause> baseCase(Context context);

    protected abstract Iterator<Ast.Clause> recursiveCase(List<Supplier<Iterator<Ast.Clause>>> recursiveCases);

    @Override
    protected ImmutableList<Ast.Elem> args() {
        return null;
    }

    @Override
    protected String format(List<String> args) {
        return null;
    }

    @Override
    public Iterator<Ast.Clause> allClauses(Context context, int depth) {
        if (depth == 0) {
            return baseCase(context);
        }

        IntFunction<Supplier<Iterator<Ast.Clause>>> mapper = x -> (() -> allClauses(context, depth - 1));

        List<Supplier<Iterator<Ast.Clause>>> children = IntStream.range(0, numChildren())
                .mapToObj(mapper)
                .collect(Collectors.toList());

        return recursiveCase(children);
    }
}
