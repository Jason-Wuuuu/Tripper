interface ServerErrorsProps {
  message: string | null;
}

export function ServerErrors({ error }: { readonly error: ServerErrorsProps }) {
  if (!error?.message) return null;
  return (
    <div className="text-error text-sm text-center italic py-2">
      {error.message}
    </div>
  );
}
