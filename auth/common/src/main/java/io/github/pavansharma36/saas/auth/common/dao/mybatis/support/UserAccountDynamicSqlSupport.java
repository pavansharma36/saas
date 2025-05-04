package io.github.pavansharma36.saas.auth.common.dao.mybatis.support;

import jakarta.annotation.Generated;
import java.sql.JDBCType;
import java.util.Date;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class UserAccountDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final UserAccount userAccount = new UserAccount();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> id = userAccount.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> username = userAccount.username;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> password = userAccount.password;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> userInfoId = userAccount.userInfoId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Boolean> enabled = userAccount.enabled;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Boolean> accountNonExpired = userAccount.accountNonExpired;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Boolean> credentialsNonExpired = userAccount.credentialsNonExpired;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Boolean> accountNonLocked = userAccount.accountNonLocked;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> createdBy = userAccount.createdBy;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> createdAt = userAccount.createdAt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> updatedBy = userAccount.updatedBy;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> updatedAt = userAccount.updatedAt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class UserAccount extends AliasableSqlTable<UserAccount> {
        public final SqlColumn<String> id = column("id", JDBCType.VARCHAR);

        public final SqlColumn<String> username = column("username", JDBCType.VARCHAR);

        public final SqlColumn<String> password = column("password", JDBCType.VARCHAR);

        public final SqlColumn<String> userInfoId = column("user_info_id", JDBCType.VARCHAR);

        public final SqlColumn<Boolean> enabled = column("enabled", JDBCType.BIT);

        public final SqlColumn<Boolean> accountNonExpired = column("account_non_expired", JDBCType.BIT);

        public final SqlColumn<Boolean> credentialsNonExpired = column("credentials_non_expired", JDBCType.BIT);

        public final SqlColumn<Boolean> accountNonLocked = column("account_non_locked", JDBCType.BIT);

        public final SqlColumn<String> createdBy = column("created_by", JDBCType.VARCHAR);

        public final SqlColumn<Date> createdAt = column("created_at", JDBCType.TIMESTAMP);

        public final SqlColumn<String> updatedBy = column("updated_by", JDBCType.VARCHAR);

        public final SqlColumn<Date> updatedAt = column("updated_at", JDBCType.TIMESTAMP);

        public UserAccount() {
            super("user_account", UserAccount::new);
        }
    }
}