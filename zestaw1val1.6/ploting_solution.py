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

col1_indexes = []
col2_indexes = []
with open('solution_cycle.txt') as s:
    for line in s:
        data = line.split()
        col1_indexes.append(int(data[0]))
        col2_indexes.append(int(data[1]))
    print "--------------"
    print col1_indexes
    print col2_indexes
    print len(x_coordinates)
    print len(y_coordinates)

for i in range(len(col1_indexes)):
    plt.plot([x_coordinates[col1_indexes[i] - 1], x_coordinates[col2_indexes[i] - 1]], [y_coordinates[col1_indexes[i] - 1], y_coordinates[col2_indexes[i] - 1]], 'k-', lw = 2)

plt.plot(x_coordinates, y_coordinates, 'ro')
plt.axis([0, 100, 0, 100])
plt.show()


