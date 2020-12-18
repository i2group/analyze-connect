# Item Type Mapping

With the addition of [gateway](./gateway-schema.md) and [connector](connector-schema.md) schemas, it is possible that
different schemas contain similar item types. i2 Analyze provides a way for you to map similar item types together.

## Examples
- For a detailed walkthrough of how to define item type mappings, see
[here](item-type-mapping-config.md).
- To learn more about how you can use item type mapping in your own i2 Analyze
deployment, see the following example using the connectors and schemas
provided in this repository:
  - [Adding a connector with a connector schema to an existing deployment with a gateway schema](./mapping-connector-to-gateway.md)

## The importance of item type mapping

Having similar item types can cause confusion for analysts, or provide inaccurate analytic results.
For example, it might cause issues if you have two distinct item types for modelling a
person. Issues can arise because all of the person data that enters the system
is split between the two item types. If the two item types for modelling people are Person-A and Person-B, then:

1. **It is confusing for users.** Analysts might not understand
   why there are two person types and if they are creating a person record,
   it may not be clear which type to choose; Person-A or Person-B.
2. **It allows incomplete analysis.** Having person data split between two
   types allows users to analyze one type without realising the other exists.
   For example, an analyst executes a query and wants to add all of the person
   records in the results to a chart. When filtering the results, it is possible for them to select the Person-A type without selecting the Person-B
   type, and only add a subset of results to the chart.
3. **It encourages duplication of data.**
   - i2 Analyze provides matching capabilities that allow users to identify
     duplicate records and unite them. However, only records of the same type can
     be matched and united. Therefore, allowing person data to be split between two types
     prevents i2 Analyze from being able to identify all the duplicate person
     records.
   - It is possible to have a seeded external search that accepts records of
     type Person-A but not Person-B. Then, in order to use the search with a
     Person-B record, an analyst could create a new Person-A record containing
     the same, or almost the same, data as the Person-B record and use that as
     the seed. The result would be two person records containing the same data.

You should aim to have a single item type for each real-world object modelled by
your schemas. To achieve this with multiple schemas providing similar types,
i2 Analyze allows you to map types defined in one schema to types defined in
another. Continuing with the example above, this means that you could, for
example, map Person-A (the source type) to Person-B (the target type). This means that:

- The only type visible to users is the target type.
- i2 Analyze maps any records of the source type that are returned by
  queries to records of the target type.
- Any seeded searches provided by connectors that accept the source type
  accept the target type. i2 Analyze applies the mapping in reverse to produce
  records of the source type, then uses these as seeds when sending requests to
  connectors.

Only users with `i2:Administrator` command access control permission are able to
define the item type mapping configuration.

## The rules of item type mapping

The process shown applies to any mapping you define. There are, however, some
restrictions on which types can be mapped to others:

- Item types in the Information Store schema cannot be the source type of a mapping
- Item types in connector schemas cannot be the target of any mappings. Only types in the Information Store schema or gateway schemas can be the
  target of mappings.
- Any item type mappings between two schemas can only go in one direction. For example,
  if you map a type from schema *A* to a type in schema *B*, you cannot then map
  a different type from schema *B* to a type in schema *A*.

## Chained mappings

If you define a mapping from type *A* to type *B* and another mapping from type
*B* to type *C*, then:

- Any records of type *A* are mapped to records of type *C* by applying both
  mappings in sequence
- Any records of type *B* are mapped to records of type *C* by applying the
  relevant mapping
- Only type *C* is visible to users

## Mapping to the Information Store schema versus a gateway schema

If your i2 Analyze deployment contains:

- an Information Store
- a gateway schema
- at least one more gateway schema or connector schema

Then for any mapping you define, you have the choice to map to either a type in
the Information Store schema, or a type in a gateway schema. It's recommended
that you map to an Information Store schema item type where possible. This means that:

1. The mapped records can be uploaded to the Information Store
2. You can find records in the Information Store that match the mapped records
   according to the system match rules, which can reduce duplicated data.
3. Information Store services can be used to analyze the mapped records. For
   example, you can perform an expand operation on the records.


<!--
- Merging two connector schemas by creating a new gateway schema and defining
  mappings (ConnectorA + ConnectorB -> Gateway)
- Adding a connector that comes with a gateway schema to an
  existing deployment with a gateway schema (GatewayA -> GatewayB)
- Mapping to the Information Store schema, demonstrating chained mappings
  (Connector -> Gateway -> InfoStore)
  -->