package edu.cmu.cs.db.peloton.test.common;

import edu.cmu.cs.db.peloton.test.generate.Context;
import edu.cmu.cs.db.peloton.test.generate.defn.SimpleSelect;

import java.sql.*;

/**
 * Created by tianyuli on 3/20/17.
 */
public class Main {
    public static void main(String[] args) throws SQLException {
        Context context = new PostgresDatabaseWrapper().getContext(args[0], Integer.parseInt(args[1]), args[2]);
        new SimpleSelect().allClauses(context, 0).forEachRemaining(a -> System.out.println(a.getClause()));
    }
}
