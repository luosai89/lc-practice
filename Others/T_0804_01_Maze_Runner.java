package Others;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

public class T_0804_01_Maze_Runner {
//    /**
//     * Finds the exit out of the maze closest to its entrance.
//     *
//     * @param pattern - the pattern of maze being run through
//     * @return the closest reachable exit to the maze, or empty if there are no reachable exits
//     */
//    public static Optional<MazeSpace> findClosestExit(MazePattern pattern) {
//        Optional<Node<MazeSpace>> entrance = MazeGenerator.generateMaze(pattern);
//        Queue<Node<MazeSpace>> unvisited = new LinkedList<>();
//        entrance.ifPresent(unvisited::add);
//        Set<Node<MazeSpace>> visited = new HashSet<>();
//        while (!unvisited.isEmpty()) {
//            Node<MazeSpace> currNode = unvisited.poll();
//            if (visited.contains(currNode)) continue;
//            visited.add(currNode);
//            if (currNode.getValue().isExit()) {
//                return Optional.of(currNode.getValue());
//            }
//            for (Node<MazeSpace> eachNeighbor : currNode.getNeighbors()) {
//                unvisited.offer(eachNeighbor);
//            }
//        }
//        return Optional.empty();
//    }
//
//    /**
//     * Finds the path to the exit out of the maze closest to its entrance.
//     *
//     * @param pattern - the pattern of maze being run through
//     * @return the path closest reachable exit to the maze, or an empty list if there are no reachable exits
//     */
//    public static List<MazeSpace> findShortestPathToExit(MazePattern pattern) {
//        Optional<Node<MazeSpace>> entrance = MazeGenerator.generateMaze(pattern);
//
//        Set<Node<MazeSpace>> visited = new HashSet<>();
//        Queue<List<Node<MazeSpace>>> unvisited = new LinkedList<>();
//        entrance.ifPresent(mazeSpace -> unvisited.offer(Arrays.asList(mazeSpace)));
//
//        while (!unvisited.isEmpty()) {
//            List<Node<MazeSpace>> currPath = unvisited.poll();
//            Node<MazeSpace> currNode = currPath.get(currPath.size() - 1);
//            if (pattern.getSpaceType(currNode.getValue().getCoordinates()) == MazeSpaceType.WALL) {
//                continue;
//            }
//            if (currNode.getValue().isExit()) {
//                return currPath.stream()
//                    .map(Node::getValue)
//                    .collect(Collectors.toList());
//            }
//            visited.add(currNode);
//            List<List<Node<MazeSpace>>> newPathsToVisit = currNode.getNeighbors().stream()
//                .filter(neighborNode -> !visited.contains(neighborNode))
//                .map(neighborNode -> generateNewPathWithNewNode(currPath, neighborNode))
//                .collect(Collectors.toList());
//            unvisited.addAll(newPathsToVisit);
//        }
//        return Collections.emptyList();
//    }
//
//    public static List<Node<MazeSpace>> generateNewPathWithNewNode(List<Node<MazeSpace>> currentPath,
//                                                                   Node<MazeSpace> newSpace) {
//        List<Node<MazeSpace>> newPath = new LinkedList<>(currentPath);
//        newPath.add(newSpace);
//        return newPath;
//    }
}
