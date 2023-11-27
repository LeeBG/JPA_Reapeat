package hellojpa.dialect;

import lombok.NoArgsConstructor;
import org.hibernate.dialect.Oracle12cDialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StandardBasicTypes;


public class MyOracleDialect extends Oracle12cDialect {
    public MyOracleDialect() {
        registerFunction("group_concat", new StandardSQLFunction("group_concat", StandardBasicTypes.STRING));
    }
}
