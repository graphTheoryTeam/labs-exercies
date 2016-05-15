import matplotlib.pyplot as plt
import numpy as np
x_coordinates = []
y_coordinates = []
indexes = []
with open('coordinates.txt') as f:
    for line in f:
        data = line.split()
        indexes.append(int(data[0]))
        x_coordinates.append(int(data[1]))
        y_coordinates.append(int(data[2]))
    print indexes
    print x_coordinates
    print y_coordinates
# first simple path, drawing segments
for i in range(len(indexes) - 1):
    plt.plot([x_coordinates[i], x_coordinates[i + 1]], [y_coordinates[i], y_coordinates[i + 1]], 'k-', lw = 2)
plt.plot([x_coordinates[len(indexes) - 1], x_coordinates[0]], [y_coordinates[len(indexes) - 1], y_coordinates[0]], 'k-', lw = 2)

#drawing pooints
plt.plot(x_coordinates, y_coordinates, 'ro')
plt.axis([0, 100, 0, 100])
plt.show()
