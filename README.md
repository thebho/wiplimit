# WIP Limit

A Scala-based simulation tool for analyzing Work-In-Progress (WIP) limits in software development workflows.

## Overview

This project simulates a multi-stage development pipeline with configurable WIP limits at each stage. It uses Monte Carlo simulation to analyze how different WIP limit configurations affect throughput and work distribution across stages.

## Target Audience

This app is designed for:

- **Software development teams** seeking to optimize their workflow pipelines and improve throughput
- **Process engineers and Agile coaches** who need to analyze and configure WIP limits for development stages
- **Engineering managers** making data-driven decisions about process configuration and resource allocation
- **Organizations practicing Lean or Kanban methodologies** looking to understand the impact of WIP constraints on delivery performance

## Project Structure

```
wiplimit/
├── src/main/scala/example/
│   └── Hello.scala          # Main simulation application
├── build.sbt                # Scala build configuration
└── README.md                # This file
```

## How It Works

The simulation models a typical software development workflow with four stages:

1. **UI** - User interface development stage
2. **Dev** - Backend/core development stage
3. **QA** - Quality assurance testing stage
4. **Done** - Completed work

### Simulation Process

- Runs 10,000 iterations of the workflow
- Each iteration simulates 10 cycles through the pipeline
- At each stage, work items are pulled based on dice rolls (1-6) modified by stage-specific modifiers
- Work can only move to the next stage if capacity is available
- Collects statistics on throughput and work distribution

### Configuration

The simulation uses modifiers for each stage to control WIP limits and flow:

```scala
val mod1 = -1  // UI stage modifier
val mod2 = 0   // Dev stage modifier
val mod3 = 1   // QA stage modifier
val mod4 = 2   // Done stage modifier
```

These modifiers adjust the dice roll outcomes, effectively controlling how much work flows through each stage.

## Output

The simulation produces statistical analysis:

- **Mean** - Average throughput across all iterations
- **Median** - Middle value of throughput distribution
- **Mode** - Most frequently occurring throughput value
- **Max** - Maximum throughput observed
- **Min** - Minimum throughput observed

Example output:
```
WIP Limit App
-1 | 0 | 1 | 2
Mean:   X
Median: Y
Mode:   Z
Max:    A
Min:    B
```

## Building and Running

### Prerequisites

- Scala 2.13 or later
- sbt (Scala Build Tool)

### Build

```bash
sbt compile
```

### Run

```bash
sbt run
```

## Use Cases

This simulation is useful for:

- Understanding the impact of WIP limits on pipeline throughput
- Comparing different WIP limit configurations
- Analyzing bottlenecks in development workflows
- Making data-driven decisions about process optimization

## Experimentation

To experiment with different WIP limit configurations, modify the modifier values in `src/main/scala/example/Hello.scala`:

```scala
val mod1 = -1  // Adjust UI stage
val mod2 = 0   // Adjust Dev stage
val mod3 = 1   // Adjust QA stage
val mod4 = 2   // Adjust Done stage
```

Then rebuild and run to see how the statistics change.

## License

See LICENSE file for details.
