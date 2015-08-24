import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collection;

import org.apache.commons.io.FilenameUtils;


/**
 * Class for traversing a given path for video links
 * 
 * @author Matt
 *
 */

public class FileLoader {
	
	public static final String[] exts = {"mpg", "avi", "mkv", "mpeg"};
	 
	/**
	 * Traverses the given file folder for files ending in the extensions provided in the final array
	 * exts above.  If they match, adds files to the supplied Collection, list. Optionally allows for sub-directories
	 * to be searched if boolean b is equal to true.
	 *  
	 * @param directory File path pointing to directory to be searched
	 * @param list Collection of paths that all matching file paths are to be added to
	 * @param b If true, search sub-directories as well.
	 * @throws IOException
	 */
	public static void addFiles(Path directory, final Collection<Path> list, boolean b) throws IOException {
		int levels = (b) ? Integer.MAX_VALUE : 0;
		Files.walkFileTree(directory, null, levels, new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attr) throws IOException {
				String ext = FilenameUtils.getExtension(file.toString());
				for (String s : exts) {
					if (ext.equals(s)) {
						list.add(file);
						break;
					}
				}
				return FileVisitResult.CONTINUE;
			}
		});
	}
	
	/**
	 * See above for documentation.  Calls addFiles method without searching sub-directories
	 */
	
	public static void addFiles(Path directory, final Collection<Path> list) throws IOException {
		addFiles(directory, list, false);
	}
}
