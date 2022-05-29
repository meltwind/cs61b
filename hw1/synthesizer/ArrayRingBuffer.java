// TODO: Make sure to make this class a part of the synthesizer package
package synthesizer;

import java.util.Iterator;


public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        this.capacity = capacity;
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        this.fillCount = 0;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring Buffer Overflow");
        }
        rb[last] = x;
        last = (1 + last) % capacity;
        fillCount += 1;

    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer Underflow");
        }
        T ret = rb[first];
        first = (1 + first) % capacity;
        fillCount -= 1;
        return ret;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer Underflow");
        }
        return rb[first];
    }

    @Override
    public Iterator<T> iterator() {
        return new iteratorClass();
    }

    private class iteratorClass implements Iterator<T> {
        private int flag = first;

        @Override
        public boolean hasNext() {
            if (last > first) {
                if (flag < last) {
                    return true;
                }
            }
            if (last < first) {
                if (flag < capacity() || (flag >= 0 && flag < last)) {
                    return true;
                }
            }
            return false;

        }

        @Override
        public T next() {
            if (hasNext()) {
                T ret = rb[flag];
                flag = (flag + 1) % capacity();
                return ret;
            }
            return null;
        }
    }

}
