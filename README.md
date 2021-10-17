# Simple Pipeline-Base Webserver

This project is a small experiment / demonstration on how to build a java-based webserver which processes requests 
using a pipeline. Every processing step is wrapped in a Task-object. An available worker takes a waiting Task, applies
the next processing step and either returns the resulting Task back to the pipeline or closes the client socket if no
more processing steps are required.

This approach is suitable for scaling and even supports dynamic load balancing by giving the pipeline user the ability
spawn / kill workers during pipeline execution.