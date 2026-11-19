# WIP Limit Simulation Tool

## Project Title & Description

**wiplimit** is a Work-In-Progress (WIP) Limit simulation tool written in Scala. It models a workflow system with multiple stages where throughput is determined by dice-roll-based random events. This tool helps visualize and analyze how WIP limits affect the overall throughput of a workflow system.

## Purpose

This project simulates a 4-stage workflow pipeline:
- **UI** → **Dev** → **QA** → **Done**

The simulation runs with configurable WIP limits for each stage and outputs comprehensive statistical analysis of throughput across multiple iterations. This allows you to experiment with different WIP limit configurations and understand their impact on workflow efficiency.

## Prerequisites

To build and run this project, you need:
- **Scala** (version 2.13 or later recommended)
- **sbt** (Scala Build Tool) version 1.0 or later

## Building & Running

### Build the Project
```bash
sbt compile
```

### Run the Simulation
```bash
sbt run
```

The simulation will execute and print statistical results to the console.

## How It Works

The simulation operates as follows:

1. **Iterations**: Runs 10,000 complete simulation iterations
2. **Steps per Iteration**: Each iteration consists of 10 workflow steps
3. **Dice Rolls**: At each stage, a 6-sided die is rolled (values 1-6) to determine throughput
4. **Stage Modifiers**: Each stage has a configurable modifier (mod1-mod4) that adjusts the dice roll result
5. **WIP Enforcement**: Work items flow through stages based on:
   - Available work in the current stage
   - Throughput capacity (dice roll + modifier)
   - WIP limits prevent bottlenecks by controlling flow between stages
6. **Throughput Tracking**: The final "Done" count minus remaining work in all stages represents the net throughput

## Configuration

The WIP limits are controlled by four modifiers that adjust the dice rolls for each stage:

- **mod1**: Modifier for UI stage (default: -1)
- **mod2**: Modifier for Dev stage (default: 0)
- **mod3**: Modifier for QA stage (default: 1)
- **mod4**: Modifier for Done stage (default: 2)

To adjust WIP limits, modify these values in `src/main/scala/example/Hello.scala`:
```scala
val mod1 = -1  // UI stage modifier
val mod2 = 0   // Dev stage modifier
val mod3 = 1   // QA stage modifier
val mod4 = 2   // Done stage modifier
```

Negative modifiers reduce throughput (creating tighter WIP limits), while positive modifiers increase throughput (relaxing WIP limits).

## Output

The simulation prints the following statistics:

- **Mean**: Average throughput across all 10,000 iterations
- **Median**: Middle value of throughput distribution (50th percentile)
- **Mode**: Most frequently occurring throughput value
- **Max**: Maximum throughput achieved in any iteration
- **Min**: Minimum throughput achieved in any iteration

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

## Project Structure

```
wiplimit/
├── README.md                          # This file
├── build.sbt                          # Scala build configuration
└── src/main/scala/example/
    └── Hello.scala                    # Main simulation application
```

### Main Application: `Hello.scala`

The `Hello.scala` file contains the complete simulation logic:
- Initializes the random number generator for dice rolls
- Defines the 4-stage workflow pipeline
- Runs 10,000 iterations of the simulation
- Collects throughput data
- Calculates and displays statistical analysis

## Getting Started

1. Clone the repository
2. Navigate to the project directory
3. Run `sbt run` to execute the simulation
4. Experiment with different modifier values to see how WIP limits affect throughput
5. Analyze the output statistics to understand workflow efficiency

## License

This project is provided as-is for educational and simulation purposes.
