FROM glassfish:4.1-jdk8
LABEL maintainer="RAVELONDRINA Harijaona  <hravelondrina@gmail.com>"
LABEL version="0.3.0"
# -----------------------------------------------------------------------------

# Copy Oracle driver into the glassfish domain libraries
COPY *.jar /usr/local/glassfish4/glassfish/domains/domain1/lib/

COPY docker-entrypoint.sh /
RUN chmod +x /docker-entrypoint.sh

ENTRYPOINT ["/docker-entrypoint.sh"]

# 80 for HTTP and 443 for HTTPS
EXPOSE 8080 4848

CMD ["asadmin", "start-domain", "-v"]
