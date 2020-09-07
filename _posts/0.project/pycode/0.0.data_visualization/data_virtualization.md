
# Data virtualization

[toc]

---

## Installing Matplotlib

ython -m pip install --user matplotlibp


## Plotting a Simple Line Graph

The `pyplot` module: contains func­tions that generate charts and plots.

the `subplots()` function: generate one or more plots in the same fig­ure. 

The variable `fig` represents the entire figure or collection of plots that are generated. 

The variable `ax` represents a single plot in the figure and is the variable we’ll use most of the time.

use the `plot()` method: plot the data it’s given in a meaningful way. 

The function `plt.show()` opens Matplotlib’s viewer and displays the plot


```py
import matplotlib.pyplot as plt 

squares = [1, 4, 9, 16, 25]

fig, ax = plt.subplots() 
ax.plot(squares)
plt.show()
```

---

## Changing the Label Type and Line Thickness

adjust every feature of a visualization


```py
import matplotlib.pyplot as plt 

squares = [1, 4, 9, 16, 25]

fig, ax = plt.subplots() 
ax.plot(squares)
plt.show()
```
















.