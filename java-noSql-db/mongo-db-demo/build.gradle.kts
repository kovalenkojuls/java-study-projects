dependencies {
    implementation("ch.qos.logback:logback-classic")
    implementation("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    implementation("com.fasterxml.jackson.core:jackson-databind")

    implementation("org.mongodb:mongodb-driver-core")
    implementation("org.mongodb:mongodb-driver-sync")
    implementation("org.mongodb:bson")
}