plugins {
    id("ktlint-kotlin-common")
    `java-library`
    `maven-publish`
}

dependencies {
    implementation("com.pinterest.ktlint:ktlint-core:0.49.1")
    implementation("com.pinterest.ktlint:ktlint-cli-ruleset-core:0.49.1")
    implementation("com.pinterest.ktlint:ktlint-rule-engine-core:0.49.1")
    implementation("com.pinterest.ktlint:ktlint-logger:0.49.1")
}
