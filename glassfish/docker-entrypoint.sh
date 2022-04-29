#!/bin/bash
if [[ -z $ADMIN_PASSWORD ]]; then
#ADMIN_PASSWORD=$(date| md5sum | fold -w 8 | head -n 1)
ADMIN_PASSWORD="admin"
echo "##########GENERATED ADMIN PASSWORD: $ADMIN_PASSWORD ##########"
fi

echo "AS_ADMIN_PASSWORD=" > /tmp/glassfishpwd
echo "AS_ADMIN_NEWPASSWORD=${ADMIN_PASSWORD}" >> /tmp/glassfishpwd

asadmin --user=admin --passwordfile=/tmp/glassfishpwd change-admin-password --domain_name domain1
asadmin start-domain

echo "AS_ADMIN_PASSWORD=${ADMIN_PASSWORD}" > /tmp/glassfishpwd

# asadmin --user=admin --passwordfile=/tmp/glassfishpwd create-jdbc-connection-pool --driverclassname com.mysql.jdbc.Driver --restype java.sql.Driver --property 'ServerName=127.0.0.1:PortNumber=3306:Password=123456:User=mysql:DatabaseName=m2_gnote:URL="jdbc:mysql://127.0.0.1:3306/m2_gnote":UseSSL=false' myjdbc/m2_gnote_pooldr

asadmin --user=admin --passwordfile=/tmp/glassfishpwd create-jdbc-connection-pool --datasourceclassname com.mysql.jdbc.jdbc2.optional.MysqlDataSource --restype javax.sql.DataSource --property 'ServerName=127.0.0.1:PortNumber=3306:Password=123456:User=mysql:DatabaseName=m2_gnote:URL="jdbc:mysql://127.0.0.1:3306/m2_gnote":UseSSL=false' myjdbc/m2_gnote_poolds

asadmin --user=admin --passwordfile=/tmp/glassfishpwd create-jdbc-resource --connectionpoolid myjdbc/m2_gnote_poolds myjdbc/m2_gnote_resds

# asadmin --user=admin --passwordfile=/tmp/glassfishpwd create-jdbc-resource --connectionpoolid myjdbc/m2_gnote_pooldr myjdbc/m2_gnote_resdr


asadmin --user=admin --passwordfile=/tmp/glassfishpwd enable-secure-admin
asadmin --user=admin stop-domain
rm /tmp/glassfishpwd

exec "$@"

