import networkx as nx
import matplotlib.pyplot as plt

def draw_graph():
    file = open('print.txt', 'r')
    all_char_set = file.read().splitlines()
    print all_char_set
    nodes = []
    edges = []
    edges_weights = []
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

    # gathering edges_weights
    for i in range(counter, len(all_char_set)):
        counter += 1
        if all_char_set[i] == '#':
            break
        edges_weights.append(all_char_set[i])
    print edges_weights

    # create networkx graph
    G = nx.Graph()

    # add nodes
    for node in nodes:
        G.add_node(node)

    # add edges
    i = 0
    for j in range(len(edges_weights)):
            G.add_edge(edges[i], edges[i + 1])
            i += 2
    # defing labels
    '''edges_labels = {}
    for i in range(len(edges_weights)):
        edges_labels[i] = edges_weights[i]
    '''
    lab = {}
    k = 0
    for p in range(len(edges_weights)):
        lab[edges[k], edges[k+1]] = edges_weights[p];
        k += 2
    print lab
    #edges_labels = nx.get_edge_attributes(G, 'weight')
    # draw graph
    pos = nx.circular_layout(G)
    nx.draw(G, pos)
    nx.draw_networkx_edge_labels(G, pos, lab, label_pos = 0.2)
    # show graph
    plt.show()

# draw example
draw_graph()
