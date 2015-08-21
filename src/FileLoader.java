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
	
	public static void addFiles(Path directory, final Collection<Path> list) throws IOException {
		Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
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
}
