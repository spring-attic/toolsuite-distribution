# toolsuite-distribution
the distribution build for the Spring Tool Suite 3 eclipse-based distribution and branding

## run the snapshot build

`mvn -Pe412 -Psnapshot clean package`

This will build the full distributions for Windows, macOS, and Linux. The final products can be found in `features/org.springsource.sts.product.e4.12/target/products/org.springsource.sts.ide`
