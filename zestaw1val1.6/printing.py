import networkx as nx
import matplotlib.pyplot as plt

def draw_graph():
    file = open('print.txt', 'r')
    all_char_set = file.read().splitlines()
    print all_char_set
    nodes = []
    edges = []
    counter = 0
    # gathering nodes
    for node in all_char_set:
        counter += 1
        if node == '#':
            break
        nodes.append(node)

    print nodes
    # gathering edges
    for i in range(counter, len(all_char_set)):
        counter += 1
        if all_char_set[i] == '#':
            break
        edges.append(all_char_set[i])
    print edges

    # create networkx graph
    G = nx.Graph()

    # add nodes
    for node in nodes:
        G.add_node(node)

    # add edges
    for i in range(len(edges)):
        if i % 2 == 0:
            G.add_edge(edges[i], edges[i + 1])

    # draw graph
    pos = nx.circular_layout(G)
    nx.draw(G, pos)

    # show graph
    plt.show()

# draw example
draw_graph()
