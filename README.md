# WIP Limit Simulator

A Scala application that simulates Work In Progress (WIP) limits using dice rolls to model workflow throughput and bottlenecks.

## Overview

This project simulates a workflow pipeline with multiple stages (UI, Development, QA, Done) and uses dice rolls to model the variability in work completion at each stage. It runs 10,000 iterations to gather statistical data about throughput under different WIP limit configurations.

## How It Works

The simulator models a workflow with four stages:
- **UI**: Initial work intake
- **Dev**: Development stage
- **QA**: Quality assurance stage
- **Done**: Completed work

Each stage has a configurable modifier that affects the dice roll (1-6) determining how much work can be completed in that iteration. Work flows through the pipeline based on capacity at each stage, simulating real-world bottlenecks.

## Building

### Prerequisites
- Java 8 or later
- sbt 1.9.7 or later

### Compile
```bash
sbt compile
```

### Run
```bash
sbt run
```

### Package
```bash
sbt assembly
```

## Output

The simulator produces statistical analysis of the workflow:
- **Mean**: Average throughput across all iterations
- **Median**: Middle value of throughput distribution
- **Mode**: Most common throughput value
- **Max**: Maximum throughput observed
- **Min**: Minimum throughput observed

## Configuration

Modify the modifiers in `src/main/scala/example/Hello.scala` to adjust WIP limits:
```scala
val mod1 = -1  // UI stage modifier
val mod2 = 0   // Dev stage modifier
val mod3 = 1   // QA stage modifier
val mod4 = 2   // Done stage modifier
```

## Project Structure

```
wiplimit/
├── build.sbt                          # Build configuration
├── project/
│   └── build.properties               # sbt version
├── src/
│   └── main/
│       └── scala/
│           └── example/
│               └── Hello.scala        # Main simulator
└── README.md                          # This file
```

## Scala Version

This project uses **Scala 2.13.12**, a stable and widely-used version of Scala with excellent performance and library support.

## License

See LICENSE file for details.
