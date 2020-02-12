package environment;

import java.util.HashMap;

// Couldn't be an inner class since Java won't allow generic classes to be
// throwable.
class ScopeException extends RuntimeException {
	ScopeException(String message) {
		super(message);
	}
}

public class Environment<K, V> {
	private class Node {
		HashMap<K, V> scope;
		Node next;

		Node() {
			this.scope = new HashMap<K, V>();
			this.next = null;
		}
	}

	private Node stack;

	public void beginScope() {
		Node n = new Node();
		n.next = this.stack;
		this.stack = n;
	}

	public void endScope() {
		if (this.stack != null) {
			this.stack = this.stack.next;
		}
	}

	public boolean inCurrentScope(K key) {
		if (this.stack != null) {
			return this.stack.scope.containsKey(key);
		}

		return false;
	}

	// Adds key/value pair to current scope, or sets a new value if the key
	// already exists in current scope.
	public void put(K key, V value) {
		if (this.stack == null) {
			// This will only happen when there's an error in the compiler
			// itself.
			throw new ScopeException("No scope in environment.");
		}
		this.stack.scope.put(key, value);
	}

	// Returns value for key anywhere in the environment, or null if not
	// found.
	public V lookup(K key) {
		for (Node n = this.stack; n != null; n = n.next) {
			if (n.scope.containsKey(key)) {
				return n.scope.get(key);
			}
		}
		return null;
	}
}
