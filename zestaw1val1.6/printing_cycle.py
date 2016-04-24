import networkx as nx
import matplotlib.pyplot as plt

def draw_graph():
    file = open('print.txt', 'r')
    all_char_set = file.read().splitlines()
    print all_char_set
    cycle = []
    nodes = []
    edges = []
    counter = 0
    # gathering cycle
    for node in all_char_set:
        counter += 1
        if node == '#':
            break
        cycle.append(node)
    print cycle
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
        flag_added = False
        if i % 2 == 0:
            # if edges are include in the cycle they are red
            # to omit drawing cycle comment unnessesery lines
            # like all this below
            for j in range(len(cycle) - 1):
                if edges[i] == cycle[j] and edges[i + 1] == cycle[j + 1]:
                    G.add_edge(edges[i], edges[i + 1], color = 'r', weight = 2)
                    flag_added = True
                    break
                elif edges[i + 1] == cycle[j] and edges[i] == cycle[j + 1]:
                    G.add_edge(edges[i], edges[i + 1], color = 'r', weight = 2)
                    flag_added = True
                    break

            if not flag_added:
                G.add_edge(edges[i], edges[i + 1], color = 'b', weight = 1)

    G.add_edge(cycle[len(cycle) - 1], cycle[0], color = 'r', weight = 2)
    # draw graph
    pos = nx.circular_layout(G)
    edges = G.edges()
    colors = [G[u][v]['color'] for u, v in edges]
    weights = [G[u][v]['weight'] for u, v in edges]
    nx.draw(G, pos, edges=edges, edge_color=colors, width=weights)

    # show graph
    plt.show()

# draw example
draw_graph()
